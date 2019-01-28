package com.zzqx.mvc.entity;

import com.jetsum.core.orm.entity.IdEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tb_content")
public class Content extends IdEntity {
	
	public static final int WAY_UPLOAD = 1;
	public static final int WAY_HANDWRITTEN = 2;
	public static final int IS_SYSTEM = 1;
	public static final int IS_NOT_SYSTEM = 0;
	
	private int serialNumber;
	private String title;
	private String fileName;
	private String subFileName;
	private String mappingName;
	private int way;  //上传文件的方式(上传文件,手动填写路径)
	private String type;
	private String suffix;
	private Long size;
	private Long subFileSize;
	private Date addTime;
	private Date updateTime;
	private int sort;
	private int isSystem;
	private int checkStatus;
	private String checkName;
	private Folder folder;
	private Set<TerminalContent> terminalContents;
	
	@OneToMany(mappedBy="content")
	public Set<TerminalContent> getTerminalContents() {
		return terminalContents;
	}
	public void setTerminalContents(Set<TerminalContent> terminalContents) {
		this.terminalContents = terminalContents;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folder_id", referencedColumnName = "id", updatable = false, insertable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSubFileName() {
		return subFileName;
	}
	public void setSubFileName(String subFileName) {
		this.subFileName = subFileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMappingName() {
		return mappingName;
	}
	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}
	public int getWay() {
		return way;
	}
	public void setWay(int way) {
		this.way = way;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(int isSystem) {
		this.isSystem = isSystem;
	}
	public Long getSubFileSize() {
		return subFileSize;
	}
	public void setSubFileSize(Long subFileSize) {
		this.subFileSize = subFileSize;
	}

	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
}
