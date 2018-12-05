package com.zzqx.support.utils.net;
 
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
/**
 * Helper class the get the external net IP address
 */
public class Host {
	
	private Host() {}
     
    public static String getExtranetIPv4Address(){
        return searchNetworkInterfaces(IPAcceptFilterFactory.getIPv4AcceptFilter());
    }
     
    public static String getExtranetIPv6Address(){
        return searchNetworkInterfaces(IPAcceptFilterFactory.getIPv6AcceptFilter());
    }
     
    public static String getExtranetIPAddress(){
        return searchNetworkInterfaces(IPAcceptFilterFactory.getIPAllAcceptFilter());
    }
    
    public static String getHardwareAddress() {
    	StringBuffer sb = new StringBuffer("");
		try {
			byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				//字节转换为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				if(str.length()==1) {
					sb.append("0"+str);
				} else {
					sb.append(str);
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			return "";
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "";
		}
		return sb.toString().toUpperCase();
    }
    
    private static String searchNetworkInterfaces(IPAcceptFilter ipFilter){
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                //Ignore Loop/virtual/Non-started network interface
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
                while (addressEnumeration.hasMoreElements()) {
                    InetAddress inetAddress = addressEnumeration.nextElement();
                    String address = inetAddress.getHostAddress();
                    if(ipFilter.accept(address)){
                        return address;
                    }
                }
            }
        } catch (SocketException e) {
            //consider log for this exception
        }
        return "";
    }
 
}
