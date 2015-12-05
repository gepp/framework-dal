package com.jdk2010.framework.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

 
public class ThreeDESUtil {

    public static String doD3DESEncrypt(String key, String value) throws UnsupportedEncodingException {
        byte[] keyByte = key.getBytes();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= value.length() / 8; i++) {
            String encryValue = "";
            if (i != value.length() / 8) {
                encryValue = value.substring(i * 8, 8*(i+1));
            } else {
                encryValue = value.substring(i * 8, value.length());
            }
            String hexValue = StringUtil.toHexString(encryValue.getBytes());
            byte[] valueByte = StringUtil.HexString2Bytes(fillLastDataBlock(hexValue));

            byte[] lseckey = new byte[8];
            byte[] rseckey = new byte[8];
            System.arraycopy(keyByte, 0, lseckey, 0, lseckey.length);
            System.arraycopy(keyByte, keyByte.length - lseckey.length, rseckey, 0, rseckey.length);
            byte[] encryptdata = null;
            encryptdata = doDESEncrypt(lseckey, valueByte); // 16字节密钥的左半部分进行DES加密
            encryptdata = doDESDecrypt(rseckey, encryptdata);// 16字节密钥的右半部分进行DES解密
            encryptdata = doDESEncrypt(lseckey, encryptdata);// 16字节密钥的左半部分进行DES加密
            sb.append(StringUtil.toHexString(encryptdata));
        }
       // System.out.println("加密后16进制："+sb.toString());
        return Base64Util.encode(StringUtil.HexString2Bytes(sb.toString()));

    }

    public static String doD3DESDecrypt(String key, String data) throws Exception {
        StringBuffer sb = new StringBuffer();
        String decryptValue="";
        byte[] keyByte = key.getBytes("utf-8");
        byte[] databyte = Base64Util.decode(data);
        String hexStr=StringUtil.toHexString(databyte);
      //  System.out.println("解密hexStr:"+hexStr);
       // System.out.println("解密hexStr:"+hexStr.length());
         for (int i = 0; i < hexStr.length() / 16; i++) {
            decryptValue = hexStr.substring(i * 16, 16*(i+1));
           // System.out.println("decryptValue:"+decryptValue);
            byte[] lseckey = new byte[8];
            byte[] rseckey = new byte[8];
            System.arraycopy(keyByte, 0, lseckey, 0, lseckey.length);
            System.arraycopy(keyByte, keyByte.length - lseckey.length, rseckey, 0, rseckey.length);
            byte[] decryptdata = null;
            decryptdata = doDESDecrypt(lseckey,StringUtil.HexString2Bytes(decryptValue)); // 16字节密钥的左半部分进行DES加密
            decryptdata = doDESEncrypt(rseckey, decryptdata);// 16字节密钥的右半部分进行DES解密
            decryptdata = doDESDecrypt(lseckey, decryptdata);// 16字节密钥的左半部分进行DES加密
            sb.append(new String(decryptdata, "utf-8"));
        }
        return sb.toString().trim();
    }

    private static byte[] doDESEncrypt(byte[] keyBytes, byte[] databyte) {
        SecretKey deskey = new SecretKeySpec(keyBytes, "DES");
        try {
            Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            databyte = c1.doFinal(databyte);
            return databyte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] doDESDecrypt(byte[] key, byte[] encryptdata) {

        SecretKey deskey = new SecretKeySpec(key, "DES");
        byte[] decryptByte = null;
        try {
            Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            decryptByte = c1.doFinal(encryptdata);
            byte[] temp = new byte[decryptByte.length];
            System.arraycopy(decryptByte, 0, temp, 0, 8);
            decryptByte = temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (decryptByte == null) {
            return null;
        } else {
            return decryptByte;
        }
    }

    public static String fillLastDataBlock(String data) {
        StringBuffer sb = new StringBuffer(data);
        while (sb.length() % 16 != 0) {
            sb.append("00");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String key = "4C324F5A4F454242";
        String value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><BUSINESS ID=\"INIT\"><NSRXX><SFZH></SFZH><DLMM></DLMM><JQBH>016000000031</JQBH><YWSQM>L2OZOEBB</YWSQM></NSRXX></BUSINESS>";
        String encrypt = doD3DESEncrypt(key, value);
        System.out.println("加密后：" + encrypt);
        String returnstr = doD3DESDecrypt(key, encrypt);
        System.out.println("解密后：" + returnstr);
    }
}
