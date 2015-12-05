package com.jdk2010.framework.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
    /**
     * base 64 encode
     * 
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     * 
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] decode(String base64Code) throws Exception {
        return new BASE64Decoder().decodeBuffer(base64Code);
    }
    public static void main(String[] args) throws Exception {
        System.out.println(new String());
    }
}
