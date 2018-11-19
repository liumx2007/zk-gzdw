package com.zzqx.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_work_position")
public class WorkPosition extends IdEntity {
	
	private String position_name;
	private Integer order_by;
	public WorkPosition(){
		
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public Integer getOrder_by() {
		return order_by;
	}
	public void setOrder_by(Integer order_by) {
		this.order_by = order_by;
	}

}
