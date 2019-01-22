package com.zzqx.mvc.vo;

/**
 *
 */
public class MsgVo {

    private Integer msgType;//消息类型
    private String watchCode;//腕表ID

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getWatchCode() {
        return watchCode;
    }

    public void setWatchCode(String watchCode) {
        this.watchCode = watchCode;
    }
}
