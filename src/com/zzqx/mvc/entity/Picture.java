package com.zzqx.mvc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_picture")
public class Picture extends IdEntity {
	
	public static final int TYPE_CAMERA = 0;
	public static final int TYPE_SCREENCAPTURE = 1;
	
	private String fileName;
	private String filePath;
	private String subFileName;
	private String subFilePath;
	private Date addTime;
	private Order order;
	private Integer type;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "order_id", updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getFileName() {
		return fileName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSubFileName() {
		return subFileName;
	}
	public void setSubFileName(String subFileName) {
		this.subFileName = subFileName;
	}
	public String getSubFilePath() {
		return subFilePath;
	}
	public void setSubFilePath(String subFilePath) {
		this.subFilePath = subFilePath;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
