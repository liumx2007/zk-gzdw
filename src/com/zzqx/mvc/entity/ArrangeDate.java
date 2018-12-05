package com.zzqx.mvc.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_arrange_date")
public class ArrangeDate extends IdEntity {
	
	private Date arrange_date;
	private Set<ArrangeDetial> detials;
	public Date getArrange_date() {
		return arrange_date;
	}

	public void setArrange_date(Date arrange_date) {
		this.arrange_date = arrange_date;
	}
	@OneToMany
	public Set<ArrangeDetial> getDetials() {
		return detials;
	}

	public void setDetials(Set<ArrangeDetial> detials) {
		this.detials = detials;
	}


}
