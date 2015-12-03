package com.jdk2010.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 标准的 Java 序列化
 *
 */
public class JavaSerializer{

   
    public byte[] serialize(Object obj) throws IOException {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } finally {
            if (oos != null)
                try {
                    oos.close();
                } catch (IOException e) {
                }
        }
    }

    public Object deserialize(byte[] bits) throws IOException {
        if (bits == null || bits.length == 0)
            return null;
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bits);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        } finally {
            if (ois != null)
                try {
                    ois.close();
                } catch (IOException e) {
                }
        }
    }

}
