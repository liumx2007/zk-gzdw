package com.zzqx.mvc.entity;

import com.jetsum.core.orm.entity.IdEntity;
import com.zzqx.mvc.vo.PersonVo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_personnel")
public class Personnel extends IdEntity {
	
	public static int USER_ROLE_MANAGER = 0;
	public static int USER_ROLE_OPERATOR = 1;
	
	private String name;
	private String sex;
	private String watch_code;
	private String photo;
//	private WorkPosition my_work;
	private  String  my_work;
	private int work_status;
	private int work_time;
	private String remark;
	private String onduty;
	private Date change_time;
	private int bind_status;
	private String passWork;

	public Personnel (){

	}

	public Personnel(PersonVo personVo){
		this.name = personVo.getName();
		this.sex = personVo.getSex();
		this.watch_code = personVo.getWatchCode();
		this.photo = personVo.getPhoto();
		this.my_work = personVo.getMyWork();
		this.work_status = personVo.getWorkState();
		this.work_time = personVo.getWorkTime();
		this.change_time = personVo.getChangeTime();
		this.passWork = personVo.getPassWork();
	}

	public String getPassWork() {
		return passWork;
	}

	public void setPassWork(String passWork) {
		this.passWork = passWork;
	}
	/**
	 * 工号
	 */
	private String job_num;
	public Date getChange_time() {
		return change_time;
	}

	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}
	/**
	 * 绑定状态（0：已绑定，未绑定）
	 */
	
//	@OneToOne
//	@JoinColumn(name="my_work")
//	public WorkPosition getMy_work() {
//		return my_work;
//	}
//
//	public void setMy_work(WorkPosition my_work) {
//		this.my_work = my_work;
//	}
//


	public String getMy_work() {
		return my_work;
	}public void setMy_work(String my_work) {
		this.my_work = my_work;
	}public String getOnduty() {
		return onduty;
	}
	public void setOnduty(String onduty) {
		this.onduty = onduty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWatch_code() {
		return watch_code;
	}
	public void setWatch_code(String watch_code) {
		this.watch_code = watch_code;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getWork_status() {
		return work_status;
	}
	public void setWork_status(int work_status) {
		this.work_status = work_status;
	}
	public int getWork_time() {
		return work_time;
	}
	public void setWork_time(int work_time) {
		this.work_time = work_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJob_num() {
		return job_num;
	}
	public void setJob_num(String job_num) {
		this.job_num = job_num;
	}
	public int getBind_status() {
		return bind_status;
	}
	public void setBind_status(int bind_status) {
		this.bind_status = bind_status;
	}
}
