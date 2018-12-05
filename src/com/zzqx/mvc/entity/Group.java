package com.zzqx.mvc.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_group")
public class Group extends IdEntity {
	
	private String name;
	private String codeName;
	private Date addTime;
	private String serverNodeMac;
	private Set<Terminal> terminals;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id")
	public Set<Terminal> getTerminals() {
		return terminals;
	}
	public void setTerminals(Set<Terminal> terminals) {
		this.terminals = terminals;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getServerNodeMac() {
		return serverNodeMac;
	}
	public void setServerNodeMac(String serverNodeMac) {
		this.serverNodeMac = serverNodeMac;
	}
}
