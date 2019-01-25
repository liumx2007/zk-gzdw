package com.zzqx.mvc.vo;

import java.util.Date;

public class DwPlayTerminalVo {

    private String id;
    /**
     * 营业厅编号
     */
    private Long hallId;
    /**
     * 终端名称
     */
    private String terminalName;
    /**
     * 别名
     */
    private String terminalAlias;
    /**
     * 链接状态(1 在线 2 不在线)
     */
//    private String linkStatus;
    /**
     * IP地址
     */
    private String ipAddress;
    /**
     * MAC地址
     */
    private String macAddress;
    /**
     * 经伟度坐标
     */
    private String longitudeLatitude;
    /**
     * 音量
     */
    private Long volume;
    /**
     * 顺序号
     */
    private Integer sortNum;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 删除标记(1 删除 0未删除)
     */
    private String delFlag;
    /**
     * 编号
     */
    private String code;
}
