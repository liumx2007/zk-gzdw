package com.zzqx.mvc.vo;

import java.util.Date;

public class PersonVo {

    private String id;
    private String name;
    private String sex;
    private String watchCode;
    private String photo;
//    private WorkPosition myWork;
    private String myWork;
    private int workState;
    private int workTime;
    private String remark;
    private String onduty;
    private Date changeTime;
    private int bindStatus;
    private String passWork;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWatchCode() {
        return watchCode;
    }

    public void setWatchCode(String watchCode) {
        this.watchCode = watchCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

//    public WorkPosition getMyWork() {
//        return myWork;
//    }
//
//    public void setMyWork(WorkPosition myWork) {
//        this.myWork = myWork;
//    }


    public String getMyWork() {
        return myWork;
    }

    public void setMyWork(String myWork) {
        this.myWork = myWork;
    }

    public int getWorkState() {
        return workState;
    }

    public void setWorkState(int workState) {
        this.workState = workState;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOnduty() {
        return onduty;
    }

    public void setOnduty(String onduty) {
        this.onduty = onduty;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public int getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(int bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getPassWork() {
        return passWork;
    }

    public void setPassWork(String passWork) {
        this.passWork = passWork;
    }
}
