package com.zzqx.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_dkh")
public class Dkh extends IdEntity {
	
	private String personnelId;
	private Integer role;
	private String name;
	private String jobNum;
	private String photo;
	private String dkhPhoto;
	
	public String getPersonnelId() {
		return personnelId;
	}
	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDkhPhoto() {
		return dkhPhoto;
	}
	public void setDkhPhoto(String dkhPhoto) {
		this.dkhPhoto = dkhPhoto;
	}
}
