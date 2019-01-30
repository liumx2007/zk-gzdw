package com.zzqx.mvc.vo;

public class DwTerminalVo {

    private int  hallId;//营业厅编号
    private String  id;//不管，修改时用的
    private String erminalName;//终端备名称
    private String ipAddress;//ip地址
    private String macAddress;//mac地址
    private String Latitude;//经纬度
    private String code;//编号（可不填，若不填，取ip地址第4位）
    private String alias;//别名设

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErminalName() {
        return erminalName;
    }

    public void setErminalName(String erminalName) {
        this.erminalName = erminalName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
