package com.jdk2010.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacAddressUtil {
    /**
     * 获取当前操作系统名称. return 操作系统名称 例如:windows,Linux,Unix等.
     */
    private static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * 获取Unix网卡的mac地址.
     * 
     * @return mac地址
     */
    private static String getUnixMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            /**
             * Unix下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
             */
            process = Runtime.getRuntime().exec("ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                /**
                 * 寻找标示字符串[hwaddr]
                 */
                index = line.toLowerCase().indexOf("hwaddr");
                /**
                 * 找到了
                 */
                if (index != -1) {
                    /**
                     * 取出mac地址并去除2边空格
                     */
                    mac = line.substring(index + "hwaddr".length() + 1).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 获取Linux网卡的mac地址.
     * 
     * @return mac地址
     */
    private static String getLinuxMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            /**
             * linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
             */
            process = Runtime.getRuntime().exec("ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("硬件地址");
                /**
                 * 找到了
                 */
                if (index != -1) {
                    /**
                     * 取出mac地址并去除2边空格
                     */
                    mac = line.substring(index + 4).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 获取widnows网卡的mac地址.
     * 
     * @return mac地址
     */
    private static String getWindowsMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            /**
             * windows下的命令，显示信息中包含有mac地址信息
             */
            process = Runtime.getRuntime().exec("ipconfig /all");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                /**
                 * 寻找标示字符串[physical address]
                 */
                // index = line.toLowerCase().indexOf("physical address");

                // line=new String(line.getBytes(),"UTF-8");
                System.out.println(line);
                index = line.toLowerCase().indexOf("物理地址");
                if (index != -1) {
                    index = line.indexOf(":");
                    if (index != -1) {
                        /**
                         * 取出mac地址并去除2边空格
                         */
                        mac = line.substring(index + 1).trim();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    public static String getMac() {
        String os = getOSName();
        System.out.println(os);
        String mac = "mac未知";
        if (os.startsWith("windows")) {
            mac = getWindowsMACAddress();
            System.out.println("本地是windows:" + mac);
        } else if (os.startsWith("linux")) {
            mac = getLinuxMACAddress();
            System.out.println("本地是Linux系统,MAC地址是:" + mac);
        } else if (os.startsWith("unix")) {
            mac = getUnixMACAddress();
            System.out.println("本地是Unix系统 MAC地址是:" + mac);
        }
        return mac;
    }

    // WindowsCmd ="cmd.exe /c echo %NUMBER_OF_PROCESSORS%";//windows的特殊
    // SolarisCmd = {"/bin/sh", "-c", "/usr/sbin/psrinfo | wc -l"};
    // AIXCmd = {"/bin/sh", "-c", "/usr/sbin/lsdev -Cc processor | wc -l"};
    // HPUXCmd = {"/bin/sh", "-c",
    // "echo \"map\" | /usr/sbin/cstm | grep CPU | wc -l "};
    // LinuxCmd = {"/bin/sh", "-c",
    // "cat /proc/cpuinfo | grep ^process | wc -l"};

    /**
     * 测试用的main方法.
     * 
     * @param argc 运行参数.
     */
    public static void main(String[] argc) {
        getMac();
    }

}
