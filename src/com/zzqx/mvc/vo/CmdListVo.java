package com.zzqx.mvc.vo;

import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdList;

import java.util.List;

public class CmdListVo extends CmdList {

    List<Cmd> cmdList;

    public List<Cmd> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Cmd> cmdList) {
        this.cmdList = cmdList;
    }
}
