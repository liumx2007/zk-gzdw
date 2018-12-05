package com.zzqx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzqx.mvc.javabean.NewsCategory;
import com.zzqx.mvc.javabean.NewsInfo;
import com.zzqx.mvc.javabean.WeatherInfo;
import com.zzqx.support.framework.task.BaseTask;
import com.zzqx.support.utils.net.Host;

public class Global {
	
	private Global() {}
	
	//定义MySQL的数据库驱动程序  
    public static final String DBDRIVER = "com.mysql.jdbc.Driver";  
    //定义MySQL数据库的连接地址  
    public static final String DBURL = "jdbc:mysql://localhost/uec?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;failOverReadOnly=false";  
    //MySQL数据库的连接用户名和连接密码  
    public static final String DBUSER = "root";  
    public static final String DBPASS = "root";
	
	//程序是否已经初始化
	public static boolean inited = false;
	//授权的ID
	public static String dogId = "1288802981";
	//授权状态
	public static int authorizeStatus = 0;
	//剩余可用时间
	public static int residue = 0;
	
	//本机IP地址
	public static String localIP = Host.getExtranetIPv4Address();
	
	//程序启动所有的任务列表
	public static Map<Class<?>, BaseTask> tasks = new HashMap<>();
	//允许跨域访问的域名
	public static String cros = "";
	//未来7天天气
	public static List<WeatherInfo> weatherFuture = new ArrayList<>();
	//实时天气
	public static WeatherInfo weatherToday = new WeatherInfo();
	//历史天气
	public static List<WeatherInfo> weatherHistory = new ArrayList<>();
	//新闻
	public static List<NewsInfo> news = new ArrayList<>();
	//新闻类型
	public static List<NewsCategory> newsCategories = new ArrayList<>();
	
	public static int importStatus = 0;
}