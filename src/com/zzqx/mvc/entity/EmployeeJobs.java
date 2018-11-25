package com.zzqx.mvc.entity;

import java.lang.Integer;
import java.util.Date;

public class EmployeeJobs {
    private int id;

    private String jobsNumber;

    private String jobsName;

    private String jobsDetails;

    private String hallId;

    private Integer scheduOrder;

    private Integer positionOrder;

    private String intervalsType;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remarks;

    private String delFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobsNumber() {
        return jobsNumber;
    }

    public void setJobsNumber(String jobsNumber) {
        this.jobsNumber = jobsNumber == null ? null : jobsNumber.trim();
    }

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName == null ? null : jobsName.trim();
    }

    public String getJobsDetails() {
        return jobsDetails;
    }

    public void setJobsDetails(String jobsDetails) {
        this.jobsDetails = jobsDetails == null ? null : jobsDetails.trim();
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId == null ? null : hallId.trim();
    }

    public Integer getScheduOrder() {
        return scheduOrder;
    }

    public void setScheduOrder(Integer scheduOrder) {
        this.scheduOrder = scheduOrder;
    }

    public Integer getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(Integer positionOrder) {
        this.positionOrder = positionOrder;
    }

    public String getIntervalsType() {
        return intervalsType;
    }

    public void setIntervalsType(String intervalsType) {
        this.intervalsType = intervalsType == null ? null : intervalsType.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}