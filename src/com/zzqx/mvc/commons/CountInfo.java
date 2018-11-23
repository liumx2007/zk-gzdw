package com.zzqx.mvc.commons;

public class CountInfo {
    //中台服务器Ip
    public static final String SERVER_IP = "http://192.168.0.2:8090";
    //营业厅id
    public static final Integer HALL_ID = 2;
    /**
     * 根据腕表编码查询人员
     */
    public static final String GET_PERSON_BY_WATCHCODE = SERVER_IP + "/api/employeeInformation/getListByWatch?";
    /**
     * 获取排班列表
     */
    public static final String GET_SCH_MSG = SERVER_IP + "/api/dwBhSchedu/watchSchedu?hallId=" + HALL_ID;
    /**
     * 获取未绑定人员列表
     */
    public static final String GET_PERSON_LIST = SERVER_IP + "/api/employeeInformation/getWatchList?hallId=" + HALL_ID;
    /**
     * 修改人员绑定
     */
    public static final String UPDATA_BIND_STATUS = SERVER_IP + "/api/employeeInformation/updateWatch?hallId=" + HALL_ID;
    /**
     * 岗位ID查岗位
     */
    public static final String JOB_BY_ID = SERVER_IP + "/api/employeeJobs/getWatchJobById?hallId=" + HALL_ID + "&jobId=";

    /**
     * 根据营业厅ID清空岗位
     */
    public static final String UPDATE_MY_WORK_BY_HALLID = SERVER_IP+"/api/employeeInformation/updateMyWorkByHallId?hallId="+HALL_ID;

    /**
     * 修改员工工作状态
     */
    public static  final String UPDATE_WORK_STATUS = SERVER_IP+"/api/employeeInformation/updateByWatchCode?";

    /**
     * 修改人员绑定状态id="+i+"&&bindState=0&watchCode="+uuid
     */
    public static final String UPDATE_PERSON_STATUS = SERVER_IP+"/api/employeeInformation/updateWatch?hallId="+HALL_ID;

    /**
     * 确认上岗
     */
    public static final String GET_JOB_BY_WATCHCODE = SERVER_IP+"/api/employeeInformation/getJobByWatchCode?hallId="+HALL_ID+"&watchCode=";

}
