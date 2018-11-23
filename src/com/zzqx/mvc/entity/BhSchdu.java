package com.zzqx.mvc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BhSchdu {
    private String id;

    private BigDecimal hallId;

    private BigDecimal employeeId;

    private BigDecimal jobsId;

    private Date scheduDate;

    private String markColor;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getHallId() {
        return hallId;
    }

    public void setHallId(BigDecimal hallId) {
        this.hallId = hallId;
    }

    public BigDecimal getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigDecimal employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getJobsId() {
        return jobsId;
    }

    public void setJobsId(BigDecimal jobsId) {
        this.jobsId = jobsId;
    }

    public Date getScheduDate() {
        return scheduDate;
    }

    public void setScheduDate(Date scheduDate) {
        this.scheduDate = scheduDate;
    }

    public String getMarkColor() {
        return markColor;
    }

    public void setMarkColor(String markColor) {
        this.markColor = markColor == null ? null : markColor.trim();
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