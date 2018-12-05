package com.zzqx.support.utils.net;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;
import java.util.TooManyListenersException;

public class Com extends Observable implements SerialPortEventListener{
	private boolean isOpen;
	private static final int PARAMS_TIMEOUT = 5000; // 超时时间 
	
	private String serialName;
	private int rate;
	private int databits;
	private int stopbits;
	private int parity;
	
	private CommPortIdentifier portId;
	private SerialPort serialPort;
	private InputStream inputStream;
	
	public Com(String serialName) {
		isOpen = false;
		this.serialName = serialName;
		rate = 9600;
		databits = 8;
		stopbits = 1;
		parity = 0;
	}
	
	public Com(String serialName, int rate, int databits, int stopbits, int parity) {
		isOpen = false;
		this.serialName = serialName;
		this.rate = rate;
		this.databits = databits;
		this.stopbits = stopbits;
		this.parity = parity;
	}
	
	public void open() {
		System.out.println("正在打开"+serialName+"串口...");
		if(isOpen) close();
		try {
			portId = CommPortIdentifier.getPortIdentifier(serialName);
			serialPort = (SerialPort) portId.open("SerialReader", PARAMS_TIMEOUT);
			inputStream = serialPort.getInputStream();
			serialPort.setSerialPortParams(rate, databits, stopbits, parity);
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			isOpen = true;
			System.out.println(serialName+"串口打开成功！");
		} catch (NoSuchPortException e) {
			System.out.println("找不到"+serialName+"串口！");
		} catch (PortInUseException e) {
			System.out.println(serialName+"被占用！");
		} catch (IOException e) {
			System.out.println(serialName+"获取输入流失败！");
		} catch (UnsupportedCommOperationException e) {
			System.out.println(serialName+"参数设置失败！");
		}catch (TooManyListenersException e) {
			System.out.println("串口"+serialName+"监听者过多！");
		}
	}
	
	public boolean send(int message) {
		if(isOpen) {
			try {
				OutputStream out = new BufferedOutputStream(serialPort.getOutputStream());
				out.write(message);
				out.flush();
				out.close();
				return true;
			} catch (IOException e) {
				System.out.println(serialName+"获取输出流失败！");
				return false;
			}
		} else {
			System.out.println("串口"+serialName+"未打开！");
			return false;
		}
	}
	
	public boolean send(byte[] message) {
		if(isOpen) {
			try {
				OutputStream out = new BufferedOutputStream(serialPort.getOutputStream());
				out.write(message);
				out.flush();
				out.close();
				return true;
			} catch (IOException e) {
				System.out.println(serialName+"获取输出流失败！");
				return false;
			}
		} else {
			System.out.println("串口"+serialName+"未打开！");
			return false;
		}
	}

	public void serialEvent(SerialPortEvent event) {
		try {// 等待1秒钟让串口把数据全部接收后在处理
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		switch(event.getEventType()) {  
	        case SerialPortEvent.BI: break;// 10  
	        case SerialPortEvent.OE: break;// 7  
	        case SerialPortEvent.FE: break;// 9  
	        case SerialPortEvent.PE: break;// 8  
	        case SerialPortEvent.CD: break;// 6  
	        case SerialPortEvent.CTS: break;// 3  
	        case SerialPortEvent.DSR: break;// 4  
	        case SerialPortEvent.RI: break;// 5  
	        case SerialPortEvent.OUTPUT_BUFFER_EMPTY: break;// 2  
	        case SerialPortEvent.DATA_AVAILABLE: // 1
	        	System.out.println("收到数据-------------------------->");
	            try {
	            	byte[] readBuffer = new byte[2048];
	            	int numBytes = 0;
	            	while (inputStream.available() > 0) {
	            		numBytes = inputStream.read(readBuffer);
	                }
	                byte[] temp = new byte[numBytes];
	         	    System.arraycopy(readBuffer, 0, temp, 0, numBytes);
	         	    System.out.println(serialName+"收到:"+new String(temp, "GBK").trim());
	                changeMessage(temp);
	                inputStream.close();
	            }
	            catch (IOException e) {
	                e.printStackTrace();
	            }
	            break;
	    }
	}
	
	public void changeMessage(byte[] message) {  
	    this.setChanged();
	    this.notifyObservers(message);
	}
	
	public void close() {
		System.out.println("正在关闭"+serialName+"串口...");
	    if (isOpen) {
	    	try {
		    	serialPort.notifyOnDataAvailable(false);
		    	serialPort.removeEventListener();
	            inputStream.close();
		    	serialPort.close();
		    	isOpen = false;
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    	System.out.println("串口"+serialName+"已关闭！");
	    }
	}

	public boolean isOpen() {
		return isOpen;
	}
}
