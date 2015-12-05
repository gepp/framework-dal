package com.jdk2010.framework.util;

public class ASCUtil {
    /**
     * 字符转ASC
     * 
     * @param st
     * @return
     */
    public static String getAscInt(String st) {
        String returnStr = "";
        byte[] gc = st.getBytes();
        for (int i = 0; i < gc.length; i++)
            returnStr = returnStr + gc[i] + "";
        return returnStr.toUpperCase();
    }

    public static String getAscHex(String st) {
        String returnStr = "";
        byte[] gc = st.getBytes();
        for (int i = 0; i < gc.length; i++)
            returnStr = returnStr + Integer.toHexString(gc[i]) + "";
        return returnStr.toUpperCase();
    }

    /**
     * ASC转字符
     * 
     * @param backnum
     * @return
     */
    public static char backchar(int backnum) {
        char strChar = (char) backnum;
        return strChar;
    }

    public static void main(String[] args) {
        System.out.println(getAscHex("L2OZOEBB"));
    }
}
