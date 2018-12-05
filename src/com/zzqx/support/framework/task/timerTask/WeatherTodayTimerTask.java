package com.zzqx.support.framework.task.timerTask;

import com.zzqx.mvc.javabean.WeatherInfo;
import com.zzqx.Global;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.Weather;

import net.sf.json.JSONObject;

public class WeatherTodayTimerTask {
	
	public void doTask() {
		PropertiesHelper props = new PropertiesHelper("config");
		String autoScan = props.readValueTrim("timer.weather.today");
		String city = props.readValueTrim("interface.weather.city");
		if("on".equalsIgnoreCase(autoScan)) {
			System.out.println("正在获取当天天气数据...");
			Weather weather = new Weather();
			String weatherDataString = weather.today(city);
			if(StringHelper.isNotBlank(weatherDataString)) {
				JSONObject weatherDataObject = JSONObject.fromObject(weatherDataString);
				String status = weatherDataObject.getString("success");
				if("1".equals(status)) {
					Global.weatherToday = (WeatherInfo) JSONObject.toBean(JSONObject.fromObject(weatherDataObject.getString("result")), WeatherInfo.class);
					System.out.println("成功获取当天天气数据！");
				} else {
					System.out.println("当天天气数据获取异常："+weatherDataObject.getString("msg"));
				}
			}
		}
	}
}