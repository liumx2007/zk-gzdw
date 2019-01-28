package com.zzqx.mvc.vo;

import org.springframework.web.multipart.MultipartFile;

public class DwFileVo {

    MultipartFile file;
    Integer businessEnumType;//上传文件的业务类型
    String userId;//用户ID(不传默认用户是 0，后面通过接口获取用户信息)
    Integer retainFileName; //保留文件名类型 0 不保留文件名  1 保留文件名 2 保留文件名，文件重复覆盖
    String saveToDb;//是否保存到公共表
    Integer sortNumber;    //文件顺序
    String serverToken;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getBusinessEnumType() {
        return businessEnumType;
    }

    public void setBusinessEnumType(Integer businessEnumType) {
        this.businessEnumType = businessEnumType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRetainFileName() {
        return retainFileName;
    }

    public void setRetainFileName(Integer retainFileName) {
        this.retainFileName = retainFileName;
    }

    public String getSaveToDb() {
        return saveToDb;
    }

    public void setSaveToDb(String saveToDb) {
        this.saveToDb = saveToDb;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getServerToken() {
        return serverToken;
    }

    public void setServerToken(String serverToken) {
        this.serverToken = serverToken;
    }
}
