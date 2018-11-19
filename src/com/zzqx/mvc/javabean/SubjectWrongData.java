package com.zzqx.mvc.javabean;

import java.util.Date;

import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.entity.Subject;

public class SubjectWrongData {
	
	private String id;
	private Subject subject;
	private Personnel personnel;
	private Date addTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
