package com.zzqx.support.framework.mina.ElectricDrive;

import org.apache.mina.core.session.IoSession;

public class ElectricDriveSession {

    private long time;
    private IoSession ioSession;
    private StringBuffer stringBuffer;
    private int type;

    public ElectricDriveSession(IoSession ioSession) {
        this.ioSession = ioSession;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public IoSession getIoSession() {
        return ioSession;
    }

    public void setIoSession(IoSession ioSession) {
        this.ioSession = ioSession;
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

