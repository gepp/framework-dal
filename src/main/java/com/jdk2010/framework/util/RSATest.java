package com.jdk2010.framework.util;

import java.util.Map;

public class RSATest {

    static String publicKey;
    static String privateKey;

    static void init() {
        try {
            Map<String, Object> keyMap = RSAUtil.genKeyPair();
            publicKey = RSAUtil.getPublicKey(keyMap);
            privateKey = RSAUtil.getPrivateKey(keyMap);
            System.out.println("公钥: \n\r" + publicKey);
            System.out.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //init();
        testRSA();
        testSign();
    }

    static void testRSA() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "铜陵地税~2015-01-01~2015-12-31";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtil.encryptByPublicKey(data, publicKey);
        String jmh = Base64Util.encode(encodedData);
        System.out.println("加密后文字：\r\n" + jmh);
        encodedData = Base64Util.decode(jmh);
        byte[] decodedData = RSAUtil.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);

    }

    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtil.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + Base64Util.encode(encodedData));
        byte[] decodedData = RSAUtil.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtil.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtil.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }

}
