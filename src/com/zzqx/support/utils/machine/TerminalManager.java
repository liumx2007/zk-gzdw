package com.zzqx.support.utils.machine;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.net.SocketDataSender;

public class TerminalManager {
	
	public static synchronized void poweron(String mac) throws UnknownHostException {
		System.out.println("启动："+mac);
		if(!StringHelper.isMac(mac)) {
			throw new UnknownHostException("非法的mac地址！");
		}
		mac = mac.toUpperCase();
		String[] macSegment = mac.split("-");
		DatagramSocket socket;
		DatagramPacket packet;
		byte[] buf = new byte[102];
		for (int i = 0; i < 6; i++) {
			buf[i] = (byte) 0xFF;
		}
		for (int i = 1; i < 17; i++) {
			for (int j = 0; j < macSegment.length; j++) {
				buf[i * 6 + j] = (byte) Integer.decode("0x"+macSegment[j]).intValue();
			}
		}
		try {
			InetAddress inet = InetAddress.getByName("255.255.255.255");
			socket = new DatagramSocket(7010);
			packet = new DatagramPacket(buf, buf.length, inet, 7010);
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void reboot(String code) throws UnknownHostException {
		System.out.println("重启："+code);
		SocketDataSender socket = new SocketDataSender();
		socket.sendToClient(code, "reboot");
	}
	
	public static synchronized void poweroff(String code) throws UnknownHostException {
		System.out.println("关闭："+code);
		SocketDataSender socket = new SocketDataSender();
		socket.sendToClient(code, "poweroff");
	}

	public static synchronized void poweronBatch(Collection<? extends String> collection) {
		collection.stream().distinct().forEach(mac->{
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				poweron(mac);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static synchronized void rebootBatch(Collection<? extends String> collection) {
		collection.stream().distinct().forEach(code->{
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				reboot(code);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static synchronized void poweroffBatch(Collection<? extends String> collection) {
		collection.stream().distinct().forEach(code->{
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				poweroff(code);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
