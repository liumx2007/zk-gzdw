package com.zzqx.support.utils.machine.hardware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.zzqx.support.utils.StringHelper;

public class HardwareHandler {
	
	private static List<HardwareMonitor> list = new CopyOnWriteArrayList<>();
	
	public static void setHardwareMonitor(String mac, String hardwareMonitorString) {
		List<Hardware> hardwares = new ArrayList<>();
		String[] monitors = hardwareMonitorString.split(";");
		if(monitors.length == 5) {
			if(StringHelper.isNotBlank(monitors[0])) {
				Hardware mainborad = new Hardware();
				mainborad.setType(HardwareType.MAINBOARD);
				mainborad.setName(monitors[0]);
				hardwares.add(mainborad);
			}
			String[] cpuString = monitors[1].split("&");
			if(cpuString.length == 3) {
				if(StringHelper.isNotBlank(cpuString[0])) {
					Hardware cpu = new Hardware();
					cpu.setType(HardwareType.CPU);
					cpu.setName(cpuString[0]);
					if(StringHelper.isDecimal(cpuString[1])) {
						cpu.setTemperature(Float.valueOf(cpuString[1]));
					}
					if(StringHelper.isDecimal(cpuString[2])) {
						cpu.setLoad(Float.valueOf(cpuString[2]));
					}
					hardwares.add(cpu);
				}
			}
			String[] aGpuString = monitors[2].split("&");
			if(aGpuString.length == 3) {
				if(StringHelper.isNotBlank(aGpuString[0])) {
					Hardware aGpu = new Hardware();
					aGpu.setType(HardwareType.GRAPHICS);
					aGpu.setName(aGpuString[0]);
					if(StringHelper.isDecimal(aGpuString[1])) {
						aGpu.setTemperature(Float.valueOf(aGpuString[1]));
					}
					if(StringHelper.isDecimal(aGpuString[2])) {
						aGpu.setLoad(Float.valueOf(aGpuString[2]));
					}
					hardwares.add(aGpu);
				}
			}
			String[] nGpuString = monitors[3].split("&");
			if(nGpuString.length == 3) {
				if(StringHelper.isNotBlank(nGpuString[0])) {
					Hardware nGpu = new Hardware();
					nGpu.setType(HardwareType.GRAPHICS);
					nGpu.setName(nGpuString[0]);
					if(StringHelper.isDecimal(nGpuString[1])) {
						nGpu.setTemperature(Float.valueOf(nGpuString[1]));
					}
					if(StringHelper.isDecimal(nGpuString[2])) {
						nGpu.setLoad(Float.valueOf(nGpuString[2]));
					}
					hardwares.add(nGpu);
				}
			}
			String[] disks = monitors[4].split(",");
			for(String d : disks) {
				String[] diskString = d.split("&");
				if(diskString.length == 2) {
					Hardware disk = new Hardware();
					disk.setType(HardwareType.HARDDISK);
					disk.setName(diskString[0]);
					if(StringHelper.isDecimal(diskString[1])) {
						disk.setTemperature(Float.valueOf(diskString[1]));
					}
					hardwares.add(disk);
				}
			}
		}
		setHardwareMonitor(mac, hardwares);
	}
	
	public static void setHardwareMonitor(String mac, List<Hardware> hardwares) {
		if(!StringHelper.isMac(mac)) return;
		boolean b = false;
		for(HardwareMonitor hardwareMonitor : list) {
			if(mac.equalsIgnoreCase(hardwareMonitor.getMac())) {
				hardwareMonitor.setHardwares(hardwares);
				b = true;
				break;
			}
		}
		if(!b) {
			HardwareMonitor hardwareMonitor = new HardwareMonitor();
			hardwareMonitor.setMac(mac);
			hardwareMonitor.setHardwares(hardwares);
			list.add(hardwareMonitor);
		}
	}
	
	public static List<Hardware> getHardwares(String mac) {
		if(!StringHelper.isMac(mac)) {
			return null;
		}
		List<Hardware> hardwares = new ArrayList<>();
		for(HardwareMonitor hardwareMonitor : list) {
			if(mac.equalsIgnoreCase(hardwareMonitor.getMac())) {
				hardwares = hardwareMonitor.getHardwares();
				break;
			}
		}
		return hardwares;
	}
	
	public static void removeHardwareMonitor(String mac) {
		if(!StringHelper.isMac(mac)) return;
		for(HardwareMonitor hardwareMonitor : list) {
			if(mac.equalsIgnoreCase(hardwareMonitor.getMac())) {
				list.remove(hardwareMonitor);
				break;
			}
		}
	}
}
