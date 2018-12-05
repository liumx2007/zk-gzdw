package com.zzqx.support.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zzqx.support.utils.file.PropertiesHelper;

public class Weather {
	
	private final String INTERFACE_WEATHER_URL = "http://api.k780.com:88/";
	
	private String appkey = "";
	private String sign = "";
	
	public Weather() {
		PropertiesHelper prop = new PropertiesHelper("config");
		appkey = prop.readValueTrim("interface.weather.appkey");
		sign = prop.readValueTrim("interface.weather.sign");
	}
	
	/**
	 * 
	 * @param city beijing/北京/1/101010100/202.104.153.201
	 * @return json
	 */
	public String today(String city) {
		String app = "weather.today";
		String params = "app="+app+"&weaid="+city+"&appkey="+appkey+"&sign="+sign;
		return getWeather(params);
	}
	
	/**
	 * 
	 * @param city beijing/北京/1/101010100/202.104.153.201
	 * @return json
	 */
	public String future(String city) {
		String app = "weather.future";
		String params = "app="+app+"&weaid="+city+"&appkey="+appkey+"&sign="+sign;
		return getWeather(params);
	}
	
	/**
	 * 
	 * @param city beijing/北京/1/101010100/202.104.153.201
	 * @return json
	 */
	public String history(String city) {
		Date date = new Date();
		return history(city, date);
	}
	
	/**
	 * 
	 * @param city beijing/北京/1/101010100/202.104.153.201
	 * @return json
	 */
	public String history(String city, Date date) {
		String app = "weather.history";
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String params = "app="+app+"&weaid="+city+"&appkey="+appkey+"&sign="+sign+"&date="+df.format(date);
		return getWeather(params);
	}
	
	private String getWeather(String params) {
		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			URL url = new URL(INTERFACE_WEATHER_URL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
			printWriter.write(params);
			printWriter.flush();
			printWriter.close();
			in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8") ); 
			String str;
			while((str = in.readLine()) != null) {  
				sb.append( str );   
			}   
		} catch (MalformedURLException e) {
			System.err.println("获取天气数据失败，无效的地址："+INTERFACE_WEATHER_URL);
			return "";
		} catch (IOException e) {
			System.err.println("获取天气数据失败，数据读取失败："+INTERFACE_WEATHER_URL);
			return "";
		} finally {
			if(conn != null) {
				conn.disconnect();
			}
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return sb.toString(); 
	}
}
