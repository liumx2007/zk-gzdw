package com.zzqx.support.framework.serialport;

import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import com.zzqx.mvc.entity.Rfid;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.PropertiesHelper;

public class ComObserver implements Observer{

	public void update(Observable o, Object arg) {
		//Com com = (Com) o;
		byte[] data = (byte[]) arg;
		try {
			PropertiesHelper prop = new PropertiesHelper("config");
			String code = prop.readValueTrim("serial.code");
			String dataString = new String(data, code).trim();
			System.out.println("接收："+dataString);
			if(StringHelper.isNotBlank(dataString)) {
				Rfid.lastRfid = dataString;
			}
			//com.send("接收成功！".getBytes());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
