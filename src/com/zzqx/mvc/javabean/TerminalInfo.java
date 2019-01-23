package com.zzqx.mvc.javabean;

import com.zzqx.support.utils.machine.hardware.Hardware;

import java.util.List;

/**
 * Created by 许宸睿 on 2017/5/13.
 */
public class TerminalInfo {

    private String name;
    private String mac;
    private String ip;
    private String codeName;
    private String status;
    private List<Hardware> hardwares;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public List<Hardware> getHardwares() {
        return hardwares;
    }

    public void setHardwares(List<Hardware> hardwares) {
        this.hardwares = hardwares;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
