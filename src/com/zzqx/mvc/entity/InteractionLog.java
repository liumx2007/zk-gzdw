package com.zzqx.mvc.entity;


import com.jetsum.core.orm.entity.IdEntity;

import java.util.Date;

public class InteractionLog  extends IdEntity{

    private String id;

    private String interactionId;

    private Date clickTime;

    private String sessionBusiness;

    private String sessionInteract;

    private Integer  folderType;

    private  Interaction interaction;

    private  Integer status;

//    private String interactName;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId == null ? null : interactionId.trim();
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
        this.sessionBusiness = sessionBusiness == null ? null : sessionBusiness.trim();
    }

    public String getSessionInteract() {
        return sessionInteract;
    }

    public void setSessionInteract(String sessionInteract) {
        this.sessionInteract = sessionInteract == null ? null : sessionInteract.trim();
    }

    public Integer getFolderType() {
        return folderType;
    }

    public void setFolderType(Integer folderType) {
        this.folderType = folderType;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

//    public String getInteractName() {
//        return interactName;
//    }
//
//    public void setInteractName(String interactName) {
//        this.interactName = interactName;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteractionLog that = (InteractionLog) o;

        if (folderType != that.folderType) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (interactionId != null ? !interactionId.equals(that.interactionId) : that.interactionId != null)
            return false;
        if (clickTime != null ? !clickTime.equals(that.clickTime) : that.clickTime != null) return false;
        if (sessionBusiness != null ? !sessionBusiness.equals(that.sessionBusiness) : that.sessionBusiness != null)
            return false;
        return sessionInteract != null ? sessionInteract.equals(that.sessionInteract) : that.sessionInteract == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (interactionId != null ? interactionId.hashCode() : 0);
        result = 31 * result + (clickTime != null ? clickTime.hashCode() : 0);
        result = 31 * result + (sessionBusiness != null ? sessionBusiness.hashCode() : 0);
        result = 31 * result + (sessionInteract != null ? sessionInteract.hashCode() : 0);
        result = 31 * result + folderType;
        return result;
    }
}