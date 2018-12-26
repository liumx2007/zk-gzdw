package com.zzqx.mvc.vo;

import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdList;

import java.util.List;

public class CmdListVo extends CmdList {

    private String ip;

    private String port;

    List<Cmd> cmdList;//tcp指令集合

    List<Cmd> clientList;//Client指令集合

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<Cmd> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Cmd> cmdList) {
        this.cmdList = cmdList;
    }

    public List<Cmd> getClientList() {
        return clientList;
    }

    public void setClientList(List<Cmd> clientList) {
        this.clientList = clientList;
    }
}
