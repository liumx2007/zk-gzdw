package com.zzqx.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_terminal_content")
public class 	TerminalContent extends IdEntity {
	
	public static int MODEL_ONCE = 1;
	public static int MODEL_LOOP = -1;
	
	private Terminal terminal;
	private Content content;
	private int model;
	private int sort;
	
	@ManyToOne
    @JoinColumn(name="terminal_id")
	public Terminal getTerminal() {
		return terminal;
	}
	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	@ManyToOne
    @JoinColumn(name="content_id")
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}
