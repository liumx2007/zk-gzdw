package com.zzqx.mvc.vo;

import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdList;

import java.util.List;

public class CmdListVo extends CmdList {

    List<Cmd> cmdList;//tcp指令集合

    List<Cmd> ClientList;//Client指令集合

    public List<Cmd> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Cmd> cmdList) {
        this.cmdList = cmdList;
    }

    public List<Cmd> getClientList() {
        return ClientList;
    }

    public void setClientList(List<Cmd> clientList) {
        ClientList = clientList;
    }
}
