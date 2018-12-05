package com.zzqx.support.framework.task.timerTask;

import com.zzqx.mvc.javabean.WeatherInfo;
import com.zzqx.Global;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.Weather;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherFutureTimerTask {
	
	public void doTask() {
		PropertiesHelper props = new PropertiesHelper("config");
		String autoScan = props.readValueTrim("timer.weather.future");
		String city = props.readValueTrim("interface.weather.city");
		if("on".equalsIgnoreCase(autoScan)) {
			System.out.println("正在获取未来7天天气数据...");
			Weather weather = new Weather();
			String weatherDataString = weather.future(city);
			if(StringHelper.isNotBlank(weatherDataString)) {
				JSONObject weatherDataObject = JSONObject.fromObject(weatherDataString);
				String status = weatherDataObject.getString("success");
				if("1".equals(status)) {
					Global.weatherFuture.clear();
					String weatherInfoString = weatherDataObject.getString("result");
					JSONArray weatherInfoArray = JSONArray.fromObject(weatherInfoString);
					for(int i = 0;i<weatherInfoArray.size();i++) {
						Global.weatherFuture.add((WeatherInfo) JSONObject.toBean(weatherInfoArray.getJSONObject(i), WeatherInfo.class));
					}
					System.out.println("成功未来7天天气数据！");
				} else {
					System.out.println("成功未来7天天气数据获取异常："+weatherDataObject.getString("msg"));
				}
			}
		}
	}
}