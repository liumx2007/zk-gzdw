package com.zzqx.mvc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_message")
public class Message extends IdEntity {
	
	public static int USER_ROLE_MANAGER = 0;
	public static int USER_ROLE_OPERATOR = 1;
	/*
	 * 标题
	 */
	private String title;
	/*
	 * 内容
	 */
	private String content;
	/*
	 * 类型
	 */
	private Integer type;
	/*
	 * 手表编号 （获取消息的根据）
	 */
	private String watch_code;
	/*
	 * 状态
	 */
	private Integer statu;
	/*
	 * 创建时间
	 */
	private String create_time;
	/*
	 * 修改时间
	 */
	private String edit_time;
	/*
	 * 创建人
	 */
	private String creator;
	private Date ordertime;
	
	
	
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(String edit_time) {
		this.edit_time = edit_time;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getWatch_code() {
		return watch_code;
	}
	public void setWatch_code(String watch_code) {
		this.watch_code = watch_code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}	
}
