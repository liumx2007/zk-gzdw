package com.zzqx.support.utils.machine;

import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.SocketDataSender;

/**
 * 工控机指令处理
 * @author xupei
 *
 */
public class IPC {
	
	public static final String CMD_ISAU = "_AU";
	public static final String CMD_HEADER_AU = "AUMSG_";
	public static final String CMD_HEADER_DC = "DCMSG_";
	public static final String CMD_SEPARATOR = "&";
	public static final String CMD_TAIL = "@";
	public static final String CMD_OPEN = "1";
	public static final String CMD_CLOSE = "0";
	
	private static class holder {
		private static IPC instance = new IPC();
	}
	
	private IPC() {}
	
	public static IPC getInstance() {
		return holder.instance;
	}
	
	public void send(String deviceCode, String operation) {
		String code = translate(deviceCode, operation);
		SocketDataSender sender = new SocketDataSender();
		PropertiesHelper props = new PropertiesHelper("config");
		sender.sendToTCPServer(props.readValue("con.ip"),
					props.readInteger("con.port"), code);
	}
	
	private String translate(String deviceCode, String operation) {
		StringBuffer sb = new StringBuffer();
		if (deviceCode.endsWith(IPC.CMD_ISAU)) { //音频
			sb.append(IPC.CMD_HEADER_AU);
		} else {
			sb.append(IPC.CMD_HEADER_DC);
		}
		sb.append(deviceCode);
		sb.append(IPC.CMD_SEPARATOR);
		sb.append(operation);
		sb.append(IPC.CMD_TAIL);
		return sb.toString();
	}
	
}
