package com.zzqx.mvc.entity;

public class Interaction {
    private String id;

    private String interactName;

    private String interactCode;

    private String previousId;

    private Integer folderType;

    private int hallId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInteractName() {
        return interactName;
    }

    public void setInteractName(String interactName) {
        this.interactName = interactName == null ? null : interactName.trim();
    }

    public String getInteractCode() {
        return interactCode;
    }

    public void setInteractCode(String interactCode) {
        this.interactCode = interactCode == null ? null : interactCode.trim();
    }

    public String getPreviousId() {
        return previousId;
    }

    public void setPreviousId(String previousId) {
        this.previousId = previousId == null ? null : previousId.trim();
    }

    public Integer getFolderType() {
        return folderType;
    }

    public void setFolderType(Integer folderType) {
        this.folderType = folderType;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interaction that = (Interaction) o;

        if (folderType != that.folderType) return false;
        if (hallId != that.hallId) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (interactName != null ? !interactName.equals(that.interactName) : that.interactName != null) return false;
        if (interactCode != null ? !interactCode.equals(that.interactCode) : that.interactCode != null) return false;
        return previousId != null ? previousId.equals(that.previousId) : that.previousId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (interactName != null ? interactName.hashCode() : 0);
        result = 31 * result + (interactCode != null ? interactCode.hashCode() : 0);
        result = 31 * result + (previousId != null ? previousId.hashCode() : 0);
        result = 31 * result + folderType;
        result = 31 * result + hallId;
        return result;
    }
}