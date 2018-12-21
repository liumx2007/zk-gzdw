package com.zzqx.mvc.entity;

import com.zzqx.mvc.commons.CountInfo;

import java.util.Map;

/**
 * Created by Administrator on 2018/11/29 0029.
 */
public class OtherDeviceEntity {
    public OtherDeviceEntity(){

    }
    public OtherDeviceEntity(String id,String type,String state,String location,String remarks,Map<String,String> socketCode){
        this.id = id;
        this.type = type;
        this.state = state;
        this.location = location;
        this.remarks = remarks;
        this.socketCode = socketCode;
    }
    /**
     *设备id
     */
    private String id;
    /**
     *设备类型
     */
    private String type;
    /**
     *设备状态
     */
    private String state;
    /**
     *设备位置
     */
    private String location;
    /**
     *操作
     */
    private String operation;
    /**
     * socket命令
     */
    private Map<String,String> socketCode;

    /**
     * 备注
     */
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Map<String, String> getSocketCode() {
        return socketCode;
    }

    public void setSocketCode(Map<String, String> socketCode) {
        this.socketCode = socketCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
