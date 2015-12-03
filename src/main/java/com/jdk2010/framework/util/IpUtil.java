package com.jdk2010.framework.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

    /**
     * 获取登录用户IP地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf("0:") != -1) {
            ip = "本地";
        }
        return ip;
    }

    public static List<String> getAllLocalIP() throws SocketException {
        List<String> localIPs = new ArrayList<String>();
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = addresses.nextElement();
                if (ip != null && !ip.isLoopbackAddress() && ip instanceof Inet4Address) {
                    localIPs.add(ip.getHostAddress());
                }
            }
        }
        return localIPs;
    }

    public static String getLocalIP() {
        try {
            List<String> allIps = getAllLocalIP();
            if (null == allIps || allIps.size() < 1) {
                return "unknown IP";
            } else {
                return allIps.get(0);
            }
        } catch (SocketException se) {
            return "unknown IP";
        }
    }

}
