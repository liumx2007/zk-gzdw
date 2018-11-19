package com.zzqx.mvc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_queue")
public class Queue extends IdEntity {
	
	private String queueNo;
	private String consName;
	private String consNo;
	private String busiCode;
	private String consTag;
	private String content;
	private Date addTime;
	
	public String getQueueNo() {
		return queueNo;
	}
	public void setQueueNo(String queueNo) {
		this.queueNo = queueNo;
	}
	public String getConsName() {
		return consName;
	}
	public void setConsName(String consName) {
		this.consName = consName;
	}
	public String getConsNo() {
		return consNo;
	}
	public void setConsNo(String consNo) {
		this.consNo = consNo;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getConsTag() {
		return consTag;
	}
	public void setConsTag(String consTag) {
		this.consTag = consTag;
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
}
