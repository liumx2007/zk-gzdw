package com.zzqx.support.utils.machine.hardware;

import cn.hutool.json.JSONUtil;
import com.zzqx.support.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HardwareHandler {
	
	private static List<HardwareMonitor> list = new CopyOnWriteArrayList<>();
	
	public static boolean setHardwareMonitor(String mac, String hardwareMonitorString) {
		List<Hardware> hardwareList = new ArrayList<>();
		try {
//			JSONArray jsonArray = JSONArray.fromObject(hardwareMonitorString);
			cn.hutool.json.JSONArray jsonArray = JSONUtil.parseArray(hardwareMonitorString);
//			JSONObject jsonObject;
			cn.hutool.json.JSONObject jsonObject;
			Hardware hardware;
//			JsonConfig config = new JsonConfig();
//			config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {
//
//				@Override
//				public String transformToJavaIdentifier(String str) {
//					char[] chars = str.toCharArray();
//					chars[0] = Character.toLowerCase(chars[0]);
//					return new String(chars);
//				}
//
//			});
//			config.setRootClass(Hardware.class);
			for (int i = 0; i < jsonArray.size(); i++) {
//				jsonObject = jsonArray.getJSONObject(i);
////				hardware = (Hardware) JSONObject.toBean(jsonObject, config);
//				hardware = (Hardware) JSONObject.toBean(jsonObject);
				jsonObject = jsonArray.getJSONObject(i);
				hardware = JSONUtil.toBean(jsonObject,Hardware.class);
				hardwareList.add(hardware);
			}
		} catch (Exception e) {
			return false;
		}
		return setHardwareMonitor(mac, hardwareList);
	}
	
	public static boolean setHardwareMonitor(String mac, List<Hardware> hardwareList) {
		if(!StringHelper.isMac(mac)) return false;
		boolean b = false;
		for(HardwareMonitor hardwareMonitor : list) {
			if(mac.equalsIgnoreCase(hardwareMonitor.getMac())) {
				hardwareMonitor.setHardwares(hardwareList);
				b = true;
				break;
			}
		}
		if(!b) {
			HardwareMonitor hardwareMonitor = new HardwareMonitor();
			hardwareMonitor.setMac(mac);
			hardwareMonitor.setHardwares(hardwareList);
			list.add(hardwareMonitor);
		}
		return true;
	}
	
	public static List<Hardware> getHardwareList(String mac) {
		if(!StringHelper.isMac(mac)) {
			return null;
		}
		return list.stream().filter(hardwareMonitor -> mac.equalsIgnoreCase(hardwareMonitor.getMac()))
				.findFirst().map(HardwareMonitor::getHardwares).orElse(new ArrayList<>());
	}
	
	public static void removeHardwareMonitor(String mac) {
		if(!StringHelper.isMac(mac)) return;
		list.stream().filter(hardwareMonitor -> mac.equalsIgnoreCase(hardwareMonitor.getMac())).findFirst().ifPresent(hardwareMonitor -> list.remove(hardwareMonitor));
	}
}
