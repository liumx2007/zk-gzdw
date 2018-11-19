package com.zzqx.mvc.javabean;

public class NewsContent {
	
	private String type;
	private NewsWords words;
	private NewsImage image;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public NewsWords getWords() {
		return words;
	}
	public void setWords(NewsWords words) {
		this.words = words;
	}
	public NewsImage getImage() {
		return image;
	}
	public void setImage(NewsImage image) {
		this.image = image;
	}
}
