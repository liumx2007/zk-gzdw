package com.zzqx.support.utils.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import cn.hutool.setting.dialect.Props;
import com.zzqx.support.utils.StringHelper;

public class PropertiesHelper {
	
	private Properties props;
	
	public PropertiesHelper(String fileName) {
		loadProperties(fileName);
	}
	
	public String readValue(String key) {
		String value = props.getProperty(key);
		if(value == null) {
			System.err.println("不存在的属性："+key);
		}
		return props==null?null:value;
	}
	
	public String readValueTrim(String key) {
		String value = this.readValue(key);
		return StringHelper.isEmpty(value)?value:value.trim();
	}
	
	public Integer readInteger(String key) {
		String value = this.readValueTrim(key);
		return StringHelper.isEmpty(value)?null:Integer.valueOf(value);
	}
	
	public Double readDouble(String key) {
		String value = this.readValueTrim(key);
		return StringHelper.isEmpty(value)?null:Double.valueOf(value);
	}
	
	public Long readLong(String key) {
		String value = this.readValueTrim(key);
		return StringHelper.isEmpty(value)?null:Long.valueOf(value);
	}
	
	public boolean readBoolean(String key) {
		String value = this.readValueTrim(key);
		return "1".equals(value)||"true".equalsIgnoreCase(value);
	}
	
	public List<String> readList(String key, String separator) {
		String value = this.readValue(key);
		return StringHelper.isEmpty(value)?new ArrayList<>():Arrays.asList(value.split(separator));
	}

	public Map<String, String> getProperties() {
		Map<String, String> map = new HashMap<>();
		Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
		while (it.hasNext()) {
            Entry<Object, Object> entry = it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            map.put(key.toString(), value.toString());
        }
		return map;
	}
	
	private void loadProperties(String fileName) {
		if(StringHelper.endsWithIgnoreCase(fileName, ".properties")) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."))+".properties";
		} else {
			fileName += ".properties";
		}
		Properties props = new Properties();
		URL url = PropertiesHelper.class.getResource("/"+fileName);
		try {
			String path = url.getPath();
			URI uri = new URI(path);
			InputStream in = new FileInputStream(uri.getPath());
//			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			props.load(new InputStreamReader(in, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.props = props;
	}
//	public static String  url(){
//		Props props = new Props("config.properties");
//		String url = props.getProperty("url");
//		return url;
//	}
}
