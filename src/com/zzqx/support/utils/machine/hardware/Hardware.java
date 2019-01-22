package com.zzqx.support.utils.machine.hardware;

public class Hardware {
	private Integer type;
	private String name = "";
	private Double load = 0d;
	private String loadUnit = "%";
	private Double temp = 0d;
	private String tempUnit = "℃";
	private Double power = 0d;
	private String powerUnit = "W";
	private Double used = 0d;
	private String usedUnit = "";
	private Double free = 0d;
	private String freeUnit = "";

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLoad() {
		return load;
	}

	public void setLoad(Double load) {
		this.load = load;
	}

	public String getLoadUnit() {
		return loadUnit;
	}

	public void setLoadUnit(String loadUnit) {
		this.loadUnit = loadUnit;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public String getTempUnit() {
		return tempUnit;
	}

	public void setTempUnit(String tempUnit) {
		this.tempUnit = tempUnit;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public String getPowerUnit() {
		return powerUnit;
	}

	public void setPowerUnit(String powerUnit) {
		this.powerUnit = powerUnit;
	}

	public Double getUsed() {
		return used;
	}

	public void setUsed(Double used) {
		this.used = used;
	}

	public String getUsedUnit() {
		return usedUnit;
	}

	public void setUsedUnit(String usedUnit) {
		this.usedUnit = usedUnit;
	}

	public Double getFree() {
		return free;
	}

	public void setFree(Double free) {
		this.free = free;
	}

	public String getFreeUnit() {
		return freeUnit;
	}

	public void setFreeUnit(String freeUnit) {
		this.freeUnit = freeUnit;
	}
}
