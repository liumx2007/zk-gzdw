package com.zzqx.support.utils.machine.hardware;

public class Hardware {
	private String name;
	private int type;
	private float temperature;
	private float load;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getLoad() {
		return load;
	}
	public void setLoad(float load) {
		this.load = load;
	}
}
