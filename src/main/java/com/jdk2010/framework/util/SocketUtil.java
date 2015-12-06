package com.jdk2010.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketUtil {
    public static void send() throws UnknownHostException, IOException {
        //58.240.74.2
        Socket client = new Socket("127.0.01", 9001);
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        out.write(("POST /InitServlet?jqbh=016000000031&ICCID=&VPDN=& HTTP/1.1\n").getBytes("gb18030"));
        out.write("Content-Type:application/octet-stream;Charset=gb18030\n".getBytes("gb18030"));
        out.write("Host:58.240.74.2:9001\n".getBytes("gb18030"));
        out.write("Content-length:224\n\n".getBytes("gb18030"));
        String key = "4C324F5A4F454242";
        String value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><BUSINESS ID=\"INIT\"><NSRXX><SFZH></SFZH><DLMM></DLMM><JQBH>016000000031</JQBH><YWSQM>L2OZOEBB</YWSQM></NSRXX></BUSINESS>";
        String encrypt = ThreeDESUtil.doD3DESEncrypt(key, value);
        out.write(encrypt.getBytes("gb18030"));
        System.out.println("加密后：" + encrypt);
        client.shutdownOutput();
        byte[] b = new byte[1024];
        int length = 0;
        String s = "";
        while ((length = in.read(b)) != -1) {
            s = s + new String(b, 0, length);
        }
        s = new String(s.getBytes(), "gb18030");
        System.out.println("服务端返回：" + s);
        out.close();
        in.close();
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        SocketUtil.send();
    }
}
