package com.zzqx.support.framework.mina.androidser;

import java.util.Date;

import org.apache.mina.core.session.IoSession;

public class AndroidMinaSession {
	private long time;
	private IoSession ioSession;
	private StringBuffer stringBuffer;
	private int type;
	private String watchCode;
	
	@SuppressWarnings("unused")
	private AndroidMinaSession(){}
	
	public AndroidMinaSession(IoSession ioSession) {
		this.ioSession = ioSession;
		this.type = AndroidConstant.SOCKET_CLIENT_TYPE_OTHER;
		this.stringBuffer = new StringBuffer();
		this.time = new Date().getTime();
	}
	
	public AndroidMinaSession(IoSession ioSession, int type) {
		this.ioSession = ioSession;
		this.type = type;
		this.stringBuffer = new StringBuffer();
		this.time = new Date().getTime();
	}
	
	public AndroidMinaSession(IoSession ioSession, int type, StringBuffer stringBuffer) {
		this.ioSession = ioSession;
		this.type = type;
		this.stringBuffer = stringBuffer;
		this.time = new Date().getTime();
	}
	
	public AndroidMinaSession(IoSession ioSession, int type, StringBuffer stringBuffer, long time) {
		this.ioSession = ioSession;
		this.type = type;
		this.stringBuffer = stringBuffer;
		this.time = time;
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

	public String getWatchCode() {
		return watchCode;
	}

	public void setWatchCode(String watchCode) {
		this.watchCode = watchCode;
	}
}
