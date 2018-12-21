package com.zzqx.mvc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_yj")
public class Yj extends IdEntity {
	
	private String content; 	//内容
	private String name; 	//姓名
	private String phone;	//电话
	private String bh;	//户号
	private Integer type;	//留言类型
	private Date addTime;	//留言时间
	private Integer isShow;	//是否显示状态
	private Integer flag;
	private Date flagTime;
	private String feedback;	//反馈内容
	private Date feedbackTime;	//反馈时间
	private Integer hasFk;		 //反馈状态（1、反馈、0、未反馈）
	private Integer status;
	private int upStatus;  //是否上传（1、已上传  0、未上传）
	
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Date getFlagTime() {
		return flagTime;
	}
	public void setFlagTime(Date flagTime) {
		this.flagTime = flagTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Integer getHasFk() {
		return hasFk;
	}
	public void setHasFk(Integer hasFk) {
		this.hasFk = hasFk;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public int getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(int upStatus) {
		this.upStatus = upStatus;
	}
}