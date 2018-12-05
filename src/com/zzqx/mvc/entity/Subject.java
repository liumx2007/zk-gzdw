package com.zzqx.mvc.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.jetsum.core.orm.entity.IdEntity;

@Entity
@Table(name = "tb_subject")
public class Subject extends IdEntity {
	
	private String content;
	private String explainDetail;
	private Integer type;
	private Set<Answer> answers;
	
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
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy(value = "sort asc")
	@JoinColumn(name = "subject_id")
	public Set<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	public String getExplainDetail() {
		return explainDetail;
	}
	public void setExplainDetail(String explainDetail) {
		this.explainDetail = explainDetail;
	}
}
