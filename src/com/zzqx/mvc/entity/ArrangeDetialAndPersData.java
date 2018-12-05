package com.zzqx.mvc.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ArrangeDetialAndPersData  {
	
	private String position;
	private String arrange_date_id;
	private Date create_time;
	private Date updata_time;
	private Set<Personnel> persons;
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getArrange_date_id() {
		return arrange_date_id;
	}
	public void setArrange_date_id(String arrange_date_id) {
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
	public Set<Personnel> getPersons() {
		return persons;
	}
	public void setPersons(Set<Personnel> persons) {
		this.persons = persons;
	}


}
