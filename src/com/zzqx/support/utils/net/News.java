package com.zzqx.support.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class News {
	
	private final String INTERFACE_NEWS_URL = "http://news.163.com/special/0001220O/news_json.js";
	
	public String get() {
		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			URL url = new URL(INTERFACE_NEWS_URL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"GB18030") ); 
			String str;
			while((str = in.readLine()) != null) {  
				sb.append( str );   
			}   
		} catch (MalformedURLException e) {
			System.err.println("网易新闻获取失败，无效的地址："+INTERFACE_NEWS_URL);
		} catch (IOException e) {
			System.err.println("网易新闻获取失败，IO流异常！");
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
		String returnString = sb.toString();
		returnString = returnString.substring(returnString.indexOf("=")+1);
        return returnString;
	}
}
