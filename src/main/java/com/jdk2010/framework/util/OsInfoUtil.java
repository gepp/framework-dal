package com.jdk2010.framework.util;


public class OsInfoUtil {

    /**
     * jdk版本
     * 
     * @return
     */
    public static String getJdkVersion() {
        return System.getProperties().getProperty("java.version");
    }

    /**
     * jdk 安装目录
     * 
     * @return
     */
    public static String getJdkHome() {
        return System.getProperties().getProperty("java.home");
    }

    /**
     * 操作系统的name
     * 
     * @return
     */
    public static String getOsName() {
        return System.getProperties().getProperty("os.name");
    }

    public static void main(String[] args)  {

    }

}