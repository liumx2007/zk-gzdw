package com.zzqx.mvc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_stat")
public class Stat extends IdEntity {

	/**
	 * 统计
	 */
	private String statDay;
	private int statu_busy;
	private int statu_free;
	private int statu_leave;
	private String person_id;
	private Date update_time;
	private Date create_time;
	private Date busy_time;
	private Date free_time;
	private Date leave_time;
	
	public String getStatDay() {
		return statDay;
	}
	public void setStatDay(String statDay) {
		this.statDay = statDay;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public Date getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}
	public Date getFree_time() {
		return free_time;
	}
	public void setFree_time(Date free_time) {
		this.free_time = free_time;
	}
	public Date getBusy_time() {
		return busy_time;
	}
	public void setBusy_time(Date busy_time) {
		this.busy_time = busy_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getStatu_leave() {
		return statu_leave;
	}
	public void setStatu_leave(int statu_leave) {
		this.statu_leave = statu_leave;
	}
	public int getStatu_free() {
		return statu_free;
	}
	public void setStatu_free(int statu_free) {
		this.statu_free = statu_free;
	}
	public int getStatu_busy() {
		return statu_busy;
	}
	public void setStatu_busy(int statu_busy) {
		this.statu_busy = statu_busy;
	}
}
