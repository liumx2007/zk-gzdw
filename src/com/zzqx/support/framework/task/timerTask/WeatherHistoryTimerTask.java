package com.zzqx.support.framework.task.timerTask;

import com.zzqx.mvc.javabean.WeatherInfo;
import com.zzqx.Global;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.Weather;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherHistoryTimerTask {
	
	public void doTask() {
		PropertiesHelper props = new PropertiesHelper("config");
		String autoScan = props.readValueTrim("timer.weather.history");
		String city = props.readValueTrim("interface.weather.city");
		if("on".equalsIgnoreCase(autoScan)) {
			System.out.println("正在获取当天历史时段天气数据...");
			Weather weather = new Weather();
			String weatherDataString = weather.history(city);
			if(StringHelper.isNotBlank(weatherDataString)) {
				JSONObject weatherDataObject = JSONObject.fromObject(weatherDataString);
				String status = weatherDataObject.getString("success");
				if("1".equals(status)) {
					Global.weatherHistory.clear();
					String weatherInfoString = weatherDataObject.getString("result");
					JSONArray weatherInfoArray = JSONArray.fromObject(weatherInfoString);
					for(int i = 0;i<weatherInfoArray.size();i++) {
						Global.weatherHistory.add((WeatherInfo) JSONObject.toBean(weatherInfoArray.getJSONObject(i), WeatherInfo.class));
					}
					System.out.println("成功获取当天历史时段天气数据！");
				} else {
					System.out.println("当天历史时段天气数据获取异常："+weatherDataObject.getString("msg"));
				}
			}
		}
	}
}