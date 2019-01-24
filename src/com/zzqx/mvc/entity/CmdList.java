package com.zzqx.mvc.entity;

import com.jetsum.core.orm.entity.IdEntity;

import java.util.Date;

public class CmdList extends IdEntity {
    private String id;

    private String directListName;

    private String directList;

    private String description;

    private String tcpIp;

    private String tcpPort;

    private String tcpSource;

    private  int hallId;
    private Date createTime;
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDirectListName() {
        return directListName;
    }

    public void setDirectListName(String directListName) {
        this.directListName = directListName == null ? null : directListName.trim();
    }

    public String getDirectList() {
        return directList;
    }

    public void setDirectList(String directList) {
        this.directList = directList == null ? null : directList.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTcpIp() {
        return tcpIp;
    }

    public void setTcpIp(String tcpIp) {
        this.tcpIp = tcpIp;
    }

    public String getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(String tcpPort) {
        this.tcpPort = tcpPort;
    }

    public String getTcpSource() {
        return tcpSource;
    }

    public void setTcpSource(String tcpSource) {
        this.tcpSource = tcpSource;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}