package com.zzqx.mvc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_arrange_detial")
public class ArrangeDetial extends IdEntity {
	
	private String person_id;
	private String position;
	private ArrangeDate arrange_date_id;
	private Date create_time;
	private Date updata_time;
	private String remark;
	private Integer part;
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPart() {
		return part;
	}
	public void setPart(Integer part) {
		this.part = part;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	@ManyToOne
	public ArrangeDate getArrange_date_id() {
		return arrange_date_id;
	}
	public void setArrange_date_id(ArrangeDate arrange_date_id) {
		this.arrange_date_id = arrange_date_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdata_time() {
		return updata_time;
	}
	public void setUpdata_time(Date updata_time) {
		this.updata_time = updata_time;
	}


}
