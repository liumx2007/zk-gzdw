package com.zzqx.support.utils;

import java.net.SocketAddress;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringHelper extends StringUtils {
	
	private StringHelper() {}
	
	public static String MD5(String string) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try { 
			byte[] strTemp = string.getBytes(); 
			MessageDigest mdTemp = MessageDigest.getInstance("MD5"); 
			mdTemp.update(strTemp); 
			byte[] md = mdTemp.digest(); 
			int j = md.length; 
			char str[] = new char[j * 2]; 
			int k = 0; 
			for (int i = 0; i < j; i++) {
				byte b = md[i]; 	 
				str[k++] = hexDigits[b >> 4 & 0xf]; 
				str[k++] = hexDigits[b & 0xf]; 
			} 
			return new String(str); 
		} catch (Exception e){
			return null;
		}
	}
	
	public static String byte2hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) { 
              hex = '0' + hex; 
            } 
            sb.append(hex);
			sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
	}
	
	public static byte[] hex2byte(String src) {
	    int l = src.length() / 2;
	    byte[] ret = new byte[l];
	    for (int i = 0; i < l; i++) {
	        int m = i * 2 + 1;
	        int n = m + 1;
	        ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));  
	    }
	    return ret;
	}

	
	private static byte uniteBytes(String src0, String src1) {  
	    return (byte) ((Byte.decode("0x" + src0) << 4) | Byte.decode("0x" + src1));
	} 

	
	public static String replaceSqlChars(String string) {
		String[] arr = { "_", "'", "\"", "|", "&", "%"};
		if (string != null && !string.equals("")) {
			for(String s : arr){
				string = string.replace(s, "`"+s);
			}
		}
		return string;
	}
	
	/**
	 * 是否是数字
	 * @param string
	 * @return
	 */
	public static boolean isDecimal(String string) {
		Pattern pattern = Pattern.compile("-?[0-9]*(\\.?)[0-9]*");
		Matcher isNum = pattern.matcher(string);
		return isNotBlank(string) && isNum.matches();
	}
	
	/**
	 * 是否是整数
	 * @param string
	 * @return
	 */
	public static boolean isNumeric(String string){
		Pattern pattern = Pattern.compile("-?[0-9]+"); 
		Matcher isNum = pattern.matcher(string);
		return isNotBlank(string) && isNum.matches();
	}

	public static boolean isMac(String mac) {
		String patternMac="^[a-fA-F0-9]{2}(-[a-fA-F0-9]{2}){5}$";
		return isNotBlank(mac) && Pattern.compile(patternMac).matcher(mac).find();
	}
	
	public static boolean isIp(String ip) {
		return isNotBlank(ip) && Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$").matcher(ip).matches(); 
	}
	
	public static String getIp(SocketAddress address) {
		if(address == null) return null;
		String stringAddress = address.toString();
		return stringAddress.substring(1, stringAddress.indexOf(":"));
	}
	
	public static Integer getPort(SocketAddress address) {
		if(address == null) return null;
		String stringAddress = address.toString();
		return Integer.valueOf(stringAddress.substring(stringAddress.indexOf(":")+1));
	}
	
    public static String getEncoding(String str) {
        String encode = "GB2312";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {  
                return encode;
            }
        } catch (Exception e) {
			e.printStackTrace();
        }  
        encode = "ISO-8859-1";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
            }  
        } catch (Exception e) {
			e.printStackTrace();
        }  
        encode = "UTF-8";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
            }  
        } catch (Exception e) {
			e.printStackTrace();
        }  
        encode = "GBK";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
            }  
        } catch (Exception e) {
			e.printStackTrace();
        }  
        return "";  
    }
}
