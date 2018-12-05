package com.zzqx.support.utils.net;
 
/**
 * Consider @FunctionalInterface for JDK8
 *
 */
public interface IPAcceptFilter {
    String IPv6KeyWord = ":";
    boolean accept(String ipAddress);
}