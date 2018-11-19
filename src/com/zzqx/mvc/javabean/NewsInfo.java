package com.zzqx.mvc.javabean;

import java.util.List;

public class NewsInfo {
	
	private String title;
	private String subTitle;
	private String url;
	private String time;
	private NewsCategory category;
	private List<NewsContent> content;
	private String source;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public NewsCategory getCategory() {
		return category;
	}
	public void setCategory(NewsCategory category) {
		this.category = category;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<NewsContent> getContent() {
		return content;
	}
	public void setContent(List<NewsContent> content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
}
