package com.zzqx.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_station")
public class Station extends IdEntity {
	
	private String stationCode;
	private String stationName;
	private String stationId;
	private String isGs;
	private String status;
	private String isthird;
	private Double longitude;
	private Double latitude;
	private Integer pointStatus;
	private Integer cdzNum;
	private Integer cdzUsedNum;
	
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getIsGs() {
		return isGs;
	}
	public void setIsGs(String isGs) {
		this.isGs = isGs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsthird() {
		return isthird;
	}
	public void setIsthird(String isthird) {
		this.isthird = isthird;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getPointStatus() {
		return pointStatus;
	}
	public void setPointStatus(Integer pointStatus) {
		this.pointStatus = pointStatus;
	}
	public Integer getCdzNum() {
		return cdzNum;
	}
	public void setCdzNum(Integer cdzNum) {
		this.cdzNum = cdzNum;
	}
	public Integer getCdzUsedNum() {
		return cdzUsedNum;
	}
	public void setCdzUsedNum(Integer cdzUsedNum) {
		this.cdzUsedNum = cdzUsedNum;
	}
}
