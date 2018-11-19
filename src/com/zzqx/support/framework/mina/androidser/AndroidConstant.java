package com.zzqx.support.framework.mina.androidser;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class AndroidConstant {

	/**
	 * 消息结束符
	 */
	public static String SOCKET_DATA_ENDING = "\r\n";
	/**
	 * 手表消息类型
	 */
	public static String SOCKET_DATA_WATCH_PREFIX = "watch_msg:";
	/**
	 * 手表成功连接类型
	 */
	public static String SOCKET_DATA_WATCH_CONN = "watch_conn:";
	public static String SOCKET_DATA_MAC = "<MACADDRESS>";
	public static String SOCKET_DATA_TRANSMIT = "<TRANSMIT>";
	public static String SOCKET_DATA_HARDWARE = "<HARDWARE>";
	public static String SOCKET_DATA_HEARTBEAT = "";
	public static String SYSTEM_nUNKNOWN_ERROR_0001 = "系统未知错误:";

	/**
	 * 客户端连接空闲超时时间
	 */
	public static int SOCKET_CLIENT_TIMEOUT = 15;
	public static int SOCKET_CLIENT_TYPE_FLASH = 0;
	public static int SOCKET_CLIENT_TYPE_OTHER = 1;

	/**
	 * 人员性别 男 key
	 */
	public static Integer PERSONNEL_GENDER_MAN_KEY = 0;
	/**
	 * 人员性别 女 key
	 */
	public static Integer PERSONNEL_GENDER_WOMAN_KEY = 1;
	
	/**
	 * 人员状态 休假  key
	 */
	public static Integer PERSONNEL_STATE_VACATION_KEY = 0;
	/**
	 * 人员状态 空闲 key
	 */
	public static Integer PERSONNEL_STATE_FREE_KEY = 1;
	/**
	 * 人员状态 忙碌  key
	 */
	public static Integer PERSONNEL_STATE_BUSYNESS_KEY = 2;
	/**
	 * 人员类型 暂离  key
	 */
	public static Integer PERSONNEL_STATE_TEMP_KEY = 3;
	
	/**
	 * 消息类型 治安   key
	 */
	public static Integer MESSAGE_TYPE_PEACE_KEY = 0;
	/**
	 * 消息类型 医疗   key
	 */
	public static Integer MESSAGE_TYPE_MEDICAL_KEY = 1;
	/**
	 * 消息类型 火警   key
	 */
	public static Integer MESSAGE_TYPE_FIREALARM_KEY = 2;
	/**
	 * 消息类型 呼叫班长   key
	 */
	public static Integer MESSAGE_TYPE_CALLMONITOR_KEY = 3;
	/**
	 * 消息类型 呼叫保安   key
	 */
	public static Integer MESSAGE_TYPE_CALLSECURITY_KEY = 4;
	/**
	 * 消息类型 集团业务   key
	 */
	public static Integer MESSAGE_TYPE_GROUPBUSINESS_KEY = 5;
	/**
	 * 消息类型 日常信息   key
	 */
	public static Integer MESSAGE_TYPE_NORMAL_KEY = 6;
	/**
	 * 消息类型 自助巡查信息   key
	 */
	public static Integer MESSAGE_TYPE_CHECK_KEY = 7;
	/**
	 * 消息类型 应答   key
	 */
	public static Integer MESSAGE_TYPE_ANSWER_KEY = 8;
	/**
	 * 消息类型 呼叫值长   key
	 */
	public static Integer MESSAGE_TYPE_SHIFT_KEY = 9;
	/**
	 * 消息类型 请假  key
	 */
	public static Integer MESSAGE_TYPE_VACATION_KEY = 10;
	
	/**
	 * 消息状态 已读   key
	 */
	public static Integer MESSAGE_STATE_READ_KEY = 0;
	/**
	 * 消息状态 未读   key
	 */
	public static Integer MESSAGE_STATE_UNREAD_KEY = 1;
	/**
	 * 消息状态 已处理   key
	 */
	public static Integer MESSAGE_STATE_PROCESSED_KEY = 2;
	/**
	 * 消息状态 已过期   key
	 */
	public static Integer MESSAGE_STATE_EXPIRED_KEY = 3;
	
	/**
	 * 人员职位 文员   key
	 */
	public static Integer PERSON_JOB_CLERK_KEY = 0;
	/**
	 * 人员职位 保安   key
	 */
	public static Integer PERSON_JOB_SECURITY_KEY = 1;
	/**
	 * 人员职位 班长   key
	 */
	public static Integer PERSON_JOB_MONITOR_KEY = 2;
	/**
	 * 人员职位 前台接待员   key
	 */
	public static Integer PERSON_JOB_DRIVER_KEY = 4;
	/**
	 * 人员职位 集团业务员   key
	 */
	public static Integer PERSON_JOB_SALESMAN_KEY = 5;
	
	/**
	 * 绑定状态 已绑定 key
	 */
	public static Integer PERSON_BIND_STATUS_BOUND_KEY = 0;
	/**
	 * 绑定状态 未绑定 key
	 */
	public static Integer PERSON_BIND_STATUS_UNBOUND_KEY = 1;
	
	/**
	 * 人员性别 男 value
	 */
	public static String PERSONNEL_GENDER_MAN_VALUE = "男";

	/**
	 * 人员性别 女 value
	 */
	public static String PERSONNEL_GENDER_WOMAN_VALUE = "女";
	
	/**
	 * 人员状态 休假  value
	 */
	public static String PERSONNEL_STATE_VACATION_VALUE = "休假";
	/**
	 * 人员状态 空闲  value
	 */
	public static String PERSONNEL_STATE_FREE_VALUE = "空闲";
	/**
	 * 人员状态 忙碌  value
	 */
	public static String PERSONNEL_STATE_BUSYNESS_VALUE = "忙碌";
	/**
	 * 人员状态 暂离  value
	 */
	public static String PERSONNEL_STATE_TEMP_VALUE = "暂离";
	
	
	/**
	 * 消息类型 治安   value
	 */
	public static String MESSAGE_TYPE_PEACE_VALUE = "治安";
	/**
	 * 消息类型 医疗   value
	 */
	public static String MESSAGE_TYPE_MEDICAL_VALUE = "医疗";
	/**
	 * 消息类型 火警   value
	 */
	public static String MESSAGE_TYPE_FIREALARM_VALUE = "火警";
	/**
	 * 消息类型  呼叫班长   value
	 */
	public static String MESSAGE_TYPE_CALLMONITOR_VALUE = "呼叫班长";
	/**
	 * 消息类型  请假   value
	 */
	public static String MESSAGE_TYPE_VACATION_VALUE = "请假";
	/**
	 * 消息类型  呼叫值长   value
	 */
	public static String MESSAGE_TYPE_SHIFT_VALUE = "呼叫值长";
	/**
	 * 消息类型  呼叫保安   value
	 */
	public static String MESSAGE_TYPE_CALLSECURITY_VALUE = "呼叫保安";
	/**
	 * 消息类型  集团业务   value
	 */
	public static String MESSAGE_TYPE_GROUPBUSINESS_VALUE = "集团业务";
	/**
	 * 消息类型  应答   value
	 */
	public static String MESSAGE_TYPE_ANSWER_VALUE = "应答";
	
	/**
	 * 消息状态 已读   value
	 */
	public static String MESSAGE_STATE_READ_VALUE = "已读";
	/**
	 * 消息状态 未读   value
	 */
	public static String MESSAGE_STATE_UNREAD_VALUE = "未读";
	/**
	 * 消息状态 已处理   value
	 */
	public static String MESSAGE_STATE_PROCESSED_VALUE = "已处理";
	/**
	 * 消息状态 已过期   value
	 */
	public static String MESSAGE_STATE_EXPIRED_VALUE = "已过期";
	
	/**
	 * 绑定状态 已绑定 value
	 */
	public static String PERSON_BIND_STATUS_BOUND_VALUE = "已绑定";
	/**
	 * 绑定状态 未绑定 value
	 */
	public static String PERSON_BIND_STATUS_UNBOUND_VALUE = "未绑定";
	
	/**
	 * 人员职位 文员   value
	 */
	public static String PERSON_JOB_CLERK_VALUE = "文员";
	/**
	 * 人员职位 保安   value
	 */
	public static String PERSON_JOB_SECURITY_VALUE = "保安";
	/**
	 * 人员职位 班长   value
	 */
	public static String PERSON_JOB_MONITOR_VALUE = "班长";
	/**
	 * 人员职位 前台接待员   value
	 */
	public static String PERSON_JOB_DRIVER_VALUE = "前台接待员";
	/**
	 * 人员职位 集团业务员   value
	 */
	public static String PERSON_JOB_SALESMAN_VALUE = "集团业务员";
	
	/**
	 * 人员绑职位
	 * <li>0 : 文员</li>
	 * <li>1 : 保安</li>
	 * <li>2 : 班长</li>
	 * <li>3 : 前台接待员</li>
	 * <li>4 : 集团业务员</li>
	 */
	public static final Map<Integer, String> PERSON_JOB_SALESMAN_MAP = new HashMap<Integer, String>() {
		{
			put(PERSON_JOB_CLERK_KEY, PERSON_JOB_CLERK_VALUE);
			put(PERSON_JOB_SECURITY_KEY, PERSON_JOB_SECURITY_VALUE);
			put(PERSON_JOB_MONITOR_KEY, PERSON_JOB_MONITOR_VALUE);
			put(PERSON_JOB_DRIVER_KEY, PERSON_JOB_DRIVER_VALUE);
			put(PERSON_JOB_SALESMAN_KEY, PERSON_JOB_SALESMAN_VALUE);
		}
	};
	
	/**
	 * 人员绑定手表状态
	 * <li>0 : 已绑定</li>
	 * <li>1 : 未绑定</li>
	 */
	public static final Map<Integer, String> PERSON_BIND_STATUS_BOUND_MAP = new HashMap<Integer, String>() {
		{
			put(PERSON_BIND_STATUS_BOUND_KEY, PERSON_BIND_STATUS_BOUND_VALUE);
			put(PERSON_BIND_STATUS_UNBOUND_KEY, PERSON_BIND_STATUS_UNBOUND_VALUE);
		}
	};
	

	/**
	 * 人员性别
	 * <li>0 : 男</li>
	 * <li>1 : 女</li>
	 */
	public static final Map<Integer, String> PERSONNEL_GENDER_MAP = new HashMap<Integer, String>() {
		{
			put(PERSONNEL_GENDER_MAN_KEY, PERSONNEL_GENDER_MAN_VALUE);
			put(PERSONNEL_GENDER_WOMAN_KEY, PERSONNEL_GENDER_WOMAN_VALUE);
		}
	};

	/**
	 * 人员状态
	 * <li>0 : 休假</li>
	 * <li>1 : 空闲</li>
	 * <li>2 : 忙碌</li>
	 * <li>3 : 暂离</li>
	 */
	public static final Map<Integer, String> PERSONNEL_STATE_MAP = new HashMap<Integer, String>() {
		{
			put(PERSONNEL_STATE_VACATION_KEY, PERSONNEL_STATE_VACATION_VALUE);
			put(PERSONNEL_STATE_FREE_KEY, PERSONNEL_STATE_FREE_VALUE);
			put(PERSONNEL_STATE_BUSYNESS_KEY, PERSONNEL_STATE_BUSYNESS_VALUE);
			put(PERSONNEL_STATE_TEMP_KEY, PERSONNEL_STATE_TEMP_VALUE);
		}
	};
	
	/**
	 * 消息类型
	 * <li>0 : 治安</li>
	 * <li>1 : 医疗</li>
	 * <li>2 : 火警</li>
	 * <li>3 : 呼叫班长</li>
	 * <li>4 : 呼叫保安</li>
	 * <li>5 : 集团业务</li>
	 */
	public static final Map<Integer, String> MESSAGE_TYPE_MAP = new HashMap<Integer, String>() {
		{
			put(MESSAGE_TYPE_PEACE_KEY, MESSAGE_TYPE_PEACE_VALUE);
			put(MESSAGE_TYPE_MEDICAL_KEY, MESSAGE_TYPE_MEDICAL_VALUE);
			put(MESSAGE_TYPE_FIREALARM_KEY, MESSAGE_TYPE_FIREALARM_VALUE);
			put(MESSAGE_TYPE_CALLMONITOR_KEY, MESSAGE_TYPE_CALLMONITOR_VALUE);
			put(MESSAGE_TYPE_CALLSECURITY_KEY, MESSAGE_TYPE_CALLSECURITY_VALUE);
			put(MESSAGE_TYPE_GROUPBUSINESS_KEY, MESSAGE_TYPE_GROUPBUSINESS_VALUE);
			put(MESSAGE_TYPE_VACATION_KEY, MESSAGE_TYPE_VACATION_VALUE);
		}
	};
	
	/**
	 * 消息类型
	 * <li>0 : 治安</li>
	 * <li>1 : 医疗</li>
	 * <li>2 : 火警</li>
	 * <li>3 : 呼叫班长</li>
	 * <li>4 : 呼叫保安</li>
	 * <li>5 : 集团业务</li>
	 * <li>6 : 日常信息</li>
	 * <li>7 : 自助区巡查</li>
	 * <li>8 : 应答</li>
	 */
	public static final Map<Integer, String> MESSAGE_TYPE_SHOW_MAP = new HashMap<Integer, String>() {
		{
			put(MESSAGE_TYPE_PEACE_KEY, "紧急信息");
			put(MESSAGE_TYPE_MEDICAL_KEY, "紧急信息");
			put(MESSAGE_TYPE_FIREALARM_KEY, "紧急信息");
			put(MESSAGE_TYPE_CALLMONITOR_KEY, "调度信息");
			put(MESSAGE_TYPE_CALLSECURITY_KEY, "调度信息");
			put(MESSAGE_TYPE_GROUPBUSINESS_KEY, "集团业务信息");
			put(MESSAGE_TYPE_NORMAL_KEY, "日常信息");
			put(MESSAGE_TYPE_CHECK_KEY, "自助区巡查");
			put(MESSAGE_TYPE_ANSWER_KEY, "应答");
			put(MESSAGE_TYPE_VACATION_KEY, "请假信息");
		}
	};

	/**
	 * 消息状态
	 * <li>0 : 已读</li>
	 * <li>1 : 未读</li>
	 * <li>2 : 已处理</li>
	 * <li>3 : 已过期</li>
	 */
	public static final Map<Integer, String> MESSAGE_STATE_MAP = new HashMap<Integer, String>() {
		{
			put(MESSAGE_STATE_READ_KEY, MESSAGE_STATE_READ_VALUE);
			put(MESSAGE_STATE_UNREAD_KEY, MESSAGE_STATE_UNREAD_VALUE);
			put(MESSAGE_STATE_PROCESSED_KEY, MESSAGE_STATE_PROCESSED_VALUE);
			put(MESSAGE_STATE_EXPIRED_KEY, MESSAGE_STATE_EXPIRED_VALUE);
		}
	};
}
