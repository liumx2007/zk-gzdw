
package com.zzqx.mvc.commons;

import cn.hutool.setting.dialect.Props;
import com.zzqx.mvc.entity.OtherDeviceEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountInfo {
    Props props =new Props("config.properties");
    /**
     * 中台服务器Ip
     */
    String serverIp = props.getStr("SERVER_IP");
    public  String SERVER_IP = serverIp;

    /**
     * 外网监系统地址
     */
    String DW_SERVER_IP = props.getStr("DW_SERVER_IP");

    /**
     * 文件系统ip
     */
    private  String FILE_SERVER_IP = props.getStr("FILE_SERVER_IP");
    /**
     * 文件系统的用户名和密码
     */
    public  String USER_NAME = props.getStr("FILE_SYSTEM_USER");
    public  String  PASSWORD = props.getStr("FILE_SYSTEM_PASSWORD");

    /**
     * 改动位置比较多，只能去除静态。
     * 营业厅id  --黄埔 2 --钟村 10  --南沙
     */
    Integer hallId = props.getInt("HALL_ID");
    public  Integer HALL_ID = Integer.valueOf(hallId);
//    public static final Integer HALL_ID = 10;
    //班长职位
    public  static  final  String BANZHANG = "大堂经理 ";
    //值长职位
    public  static  final  String ZHIZHANG = "大堂经理 ";
    //保安职位
    public  static  final  String BAOAN = "保安 ";

    /**
     * 本机的ip及port
     */
    public static  final  String LOCALHOST = "http://127.0.0.1:8091/server/r/cmd/send?";
    /**
     * 根据腕表编码查询人员
     */
    public   String GET_PERSON_BY_WATCHCODE = SERVER_IP + "/api/employeeInformation/getListByWatch?";
    /**
     * 获取排班列表
     */
    public  String GET_SCH_MSG = SERVER_IP + "/api/dwBhSchedu/watchSchedu?hallId=" + HALL_ID;
    /**
     * 获取未绑定人员列表
     */
    public  String GET_PERSON_LIST = SERVER_IP + "/api/employeeInformation/getWatchList?hallId=" + HALL_ID;
    /**
     * 修改人员绑定
     */
    public   String UPDATA_BIND_STATUS = SERVER_IP + "/api/employeeInformation/updateWatch?hallId=" + HALL_ID;
    /**
     * 岗位ID查岗位
     */
    public   String JOB_BY_ID = SERVER_IP + "/api/employeeJobs/getWatchJobById?hallId=" + HALL_ID + "&jobId=";

    /**
     * 根据营业厅ID清空岗位
     */
    public    String UPDATE_MY_WORK_BY_HALLID = SERVER_IP+"/api/employeeInformation/updateMyWorkByHallId?hallId="+HALL_ID;

    /**
     * 修改员工工作状态
     */
    public    String UPDATE_WORK_STATUS = SERVER_IP+"/api/employeeInformation/updateByWatchCode?";

    /**
     * 修改人员绑定状态id="+i+"&&bindState=0&watchCode="+uuid
     */
    public   String UPDATE_PERSON_STATUS = SERVER_IP+"/api/employeeInformation/updateWatch?hallId="+HALL_ID;

    /**
     * 确认上岗
     */
    public   String GET_JOB_BY_WATCHCODE = SERVER_IP+"/api/employeeInformation/getJobByWatchCode?hallId="+HALL_ID+"&watchCode=";
    /**
     * 每分钟获取排版变动信息
     * http://192.168.43.46:8090/api/dwBhSchedu/synchronousData?hallId=2
     */
    public   String GET_MESSAGE_SCHEDU = SERVER_IP+"/api/dwBhSchedu/synchronousData?hallId="+HALL_ID;
    /* *
         * 获取排班和人员信息同步（每天）
         * http://192.168.43.46:8090/api/synchronousData/getList?hallId=2
         */
    public   String GET_SYNC_DAYA = SERVER_IP+"/api/synchronousData/getList?hallId="+HALL_ID;
    /* *
     * 上传环境数据
     * http://192.168.0.109:8090/api/cloudViewEnvironment/save
     */
    public   String POST_ENVIRONMENT_DATA = SERVER_IP+"/api/cloudViewEnvironment/save?hallId="+HALL_ID;

    /**
     * 上传交互数据
     */
    public   String POST_INTERACTIONLOG_DATA = SERVER_IP+"/api/dwInteractionLog/save?hallId="+HALL_ID;
    /**
     * 上传消息数据
     */
    public   String POST_MESSAGE_DATA = SERVER_IP+"/api/dwMessage/save";
    /**
     * 上传意见簿数据
     */
    public    String POST_YJ_DATA = SERVER_IP+"/api/dtTbYj/save";

    /**
     * 外网监控登录
     */
    public  String  DW_SYSTEM_LOGIN = DW_SERVER_IP+"/api/sys/login";
    /**
     * 监控系统登录后获取用户信息
     */
    public String FILE_SYSTEM_USER_INFO = FILE_SERVER_IP+"";
    /**
     * 文件系统文件上传
     */
    public String FILE_UPLOAD = FILE_SERVER_IP + "/hall-file/api/fileUpload/singleFile";

    /**
     * 监控设备新增
     */
    public String  DW_TERMINAL_ADD = DW_SERVER_IP +"/api/employeeInformation/addOrUpdatePlayTerminal";
    /**
     * 监控设备更新
     */
    public String  DW_TERMINAL_UPDATE = DW_SERVER_IP + "/api/employeeInformation/addOrUpdatePlayTerminal";
    /**
     * 监控设备删除
     */
    public String  DW_TERMINAL_DELETE = DW_SERVER_IP + "/api/employeeInformation/delTerminal";

    /**
     *设备灯光类型
     */
    public static final String LIGHT_TYPE = "1";
    /**
     *设备空调类型
     */
    public static final String CONDITION_TYPE = "2";
    /**
     *设备门禁类型
     */
    public static final String DOOR_TYPE = "3";
    /**
     *设备屏幕类型
     */
    public static final String SCREEN_TYPE = "4";
    /**
     *设备操状态开
     */
    public static final String DEVICE_STATE_ON = "1";
    /**
     *设备操状态关
     */
    public static final String DEVICE_STATE_OFF = "0";

    /**
     * 设备列表
     */
    public static final List<OtherDeviceEntity> DEVICE_LIST = new ArrayList<>();
    static {
        DEVICE_LIST.add(new OtherDeviceEntity("1","1","0","",
                "自助终端顶射灯到休闲区顶射灯，电动汽车区后走廊顶射灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG01&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG01&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("2","1","0","",
                "休闲区顶射灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG02&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG02&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("3","1","0","",
                "顶部灯带",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG03&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG03&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("4","1","0","",
                "大厅内侧筒灯1",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG04&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG04&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("5","1","0","",
                "大厅外侧筒灯2",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG05&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG05&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("6","1","0","",
                "大厅中部园顶顶部灯膜+射灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG06&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG06&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("7","1","0","",
                "业务办理区顶部射灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG07&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG07&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("8","1","0","",
                "业务办理区射灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG08&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG08&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("9","1","0","",
                "后台区顶部灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG09&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG09&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("10","1","0","",
                "logo墙后走道顶部灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG10&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG10&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("11","1","0","",
                "业务办理区墙面灯背景灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG11&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG11&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("12","1","0","",
                "logo墙logo+vipLOGO背景灯",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DG12&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DG12&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("13","4","0","",
                "2*3拼接屏+透明屏电源",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DY01&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DY01&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("14","4","0","",
                "圆柱台面屏电源",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DY02&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DY02&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("15","1","0","",
                "大厅沙发底部led",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_HJ_DY03&1@");
                        //设备关
                        put("device_off","DCMSG_GY_HJ_DY03&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("16","1","0","",
                "所有灯光",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_BAT_DG&1@");
                        //设备关
                        put("device_off","DCMSG_GY_BAT_DG&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("17","1","0","",
                "所有电源",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_BAT_DY&1@");
                        //设备关
                        put("device_off","DCMSG_GY_BAT_DY&2@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("18","1","0","",
                "大厅筒灯组",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_BAT1&1@");
                        //设备关
                        put("device_off","DCMSG_GY_BAT1&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("19","1","0","",
                "大厅射灯灯带组",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_BAT2&1@");
                        //设备关
                        put("device_off","DCMSG_GY_BAT2&0@");
                    }
                }));
        DEVICE_LIST.add(new OtherDeviceEntity("20","1","0","",
                "背景灯组",
                new HashMap<String,String>(){
                    {
                        //设备开
                        put("device_on","DCMSG_GY_BAT3&1@");
                        //设备关
                        put("device_off","DCMSG_GY_BAT3&0@");
                    }
                }));
    }

}

