package com.zzqx.support.utils.net;
 
public class IPAcceptFilterFactory {
	private IPAcceptFilterFactory() {}
	
    public static IPAcceptFilter getIPAllAcceptFilter(){
        return IPAllAcceptFilter.getInstance();
    }
    public static IPAcceptFilter getIPv4AcceptFilter(){
        return IPv4AcceptFilter.getInstance();
    }
    public static IPAcceptFilter getIPv6AcceptFilter(){
        return IPv6AcceptFilter.getInstance();
    }
}