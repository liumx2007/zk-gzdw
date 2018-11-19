package com.zzqx.mvc.javabean;

import java.util.List;

import com.jetsum.core.orm.entity.IdEntity;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ReturnData {
	private Long total;
	private List<? extends IdEntity> rows;
	
	public ReturnData(Long total, List<? extends IdEntity> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<? extends IdEntity> getRows() {
		return rows;
	}
	public void setRows(List<? extends IdEntity> rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
	
	public String toString(JsonConfig jsonConfig) {
		return JSONObject.fromObject(this, jsonConfig).toString();
	}
}
