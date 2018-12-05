package com.zzqx.support.utils.machine.hardware;

import java.util.List;

public class HardwareMonitor {
	
	private String mac;
	private List<Hardware> hardwares;
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public List<Hardware> getHardwares() {
		return hardwares;
	}
	public void setHardwares(List<Hardware> hardwares) {
		this.hardwares = hardwares;
	}
}
