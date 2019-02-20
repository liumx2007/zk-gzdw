package com.zzqx.mvc.entity;

public class DelTerminal {
    private Integer id;

    private String terminalIds;

    private Integer tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerminalIds() {
        return terminalIds;
    }

    public void setTerminalIds(String terminalIds) {
        this.terminalIds = terminalIds == null ? null : terminalIds.trim();
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }
}