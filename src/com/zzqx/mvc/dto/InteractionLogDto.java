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
}
