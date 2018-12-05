package com.zzqx.mvc.dto;

import java.util.Date;

public class InteractionLogDto {

    private String id;

    private String interactionId;

    private Date clickTime;

    private String sessionBusiness;

    private String sessionInteract;

    private Integer folderType;

    private String interactName;

    private int  limit0;

    private int  limit1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public Date getClickTime() {
        return clickTime;
    }

    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }

    public String getSessionBusiness() {
        return sessionBusiness;
    }

    public void setSessionBusiness(String sessionBusiness) {
        this.sessionBusiness = sessionBusiness;
    }

    public String getSessionInteract() {
        return sessionInteract;
    }

    public void setSessionInteract(String sessionInteract) {
        this.sessionInteract = sessionInteract;
    }

    public Integer getFolderType() {
        return folderType;
    }

    public void setFolderType(Integer folderType) {
        this.folderType = folderType;
    }

    public String getInteractName() {
        return interactName;
    }

    public void setInteractName(String interactName) {
        this.interactName = interactName;
    }

    public int getLimit0() {
        return limit0;
    }

    public void setLimit0(int limit0) {
        this.limit0 = limit0;
    }

    public int getLimit1() {
        return limit1;
    }

    public void setLimit1(int limit1) {
        this.limit1 = limit1;
    }
}
