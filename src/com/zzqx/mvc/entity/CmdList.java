package com.zzqx.mvc.entity;

import com.jetsum.core.orm.entity.IdEntity;

public class CmdList extends IdEntity {
    private String id;

    private String directListName;

    private String directList;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDirectListName() {
        return directListName;
    }

    public void setDirectListName(String directListName) {
        this.directListName = directListName == null ? null : directListName.trim();
    }

    public String getDirectList() {
        return directList;
    }

    public void setDirectList(String directList) {
        this.directList = directList == null ? null : directList.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}