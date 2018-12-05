package com.zzqx.support.framework.mina;

public class Mina {
	public static String SOCKET_DATA_ENDING = "\r\n";
	public static String SOCKET_DATA_CLIENT = "<FLASHCLIENT>";
	public static String SOCKET_DATA_CONTROLLER = "<CONTROLLER>";
	public static String SOCKET_DATA_MAC = "<MACADDRESS>";
	public static String SOCKET_DATA_TRANSMIT = "<TRANSMIT>";
	public static String SOCKET_DATA_HARDWARE = "<HARDWARE>";
	public static String SOCKET_DATA_HEARTBEAT = "";
	
	public static int SOCKET_CLIENT_TIMEOUT = 15;
	public static int SOCKET_CLIENT_TYPE_FLASH = 0;
	public static int SOCKET_CLIENT_TYPE_OTHER = 1;
	public static int SOCKET_CLIENT_TYPE_CONTROLLER = 2;
}