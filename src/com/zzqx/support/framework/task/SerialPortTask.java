package com.zzqx.support.framework.task;

import com.zzqx.support.framework.serialport.ComObserver;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.Com;

public class SerialPortTask extends BaseTask {
	
	private Com com;

	public void beforeTask() {
	}
	
	public void doTask() {
		PropertiesHelper props = new PropertiesHelper("config");
		com = new Com(props.readValue("serial.name"));
		com.open();
		ComObserver observer = new ComObserver();
		com.addObserver(observer);
	}

	public void afterTask() {
	}

	public void stopTask() {
//		System.out.println("关闭任务：serialTask");
//		if(serial != null) {
//			serial.close();
//			serial.deleteObservers();
//		}
	}
}
