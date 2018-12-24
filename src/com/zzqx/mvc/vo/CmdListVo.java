package com.zzqx.mvc.vo;

import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdList;

import java.util.List;

public class CmdListVo extends CmdList {

    List<Cmd> cmdList;

    List<String> directTest;

    public List<Cmd> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Cmd> cmdList) {
        this.cmdList = cmdList;
    }

    public List<String> getDirectTest() {
        return directTest;
    }

    public void setDirectTest(List<String> directTest) {
        this.directTest = directTest;
    }
}
