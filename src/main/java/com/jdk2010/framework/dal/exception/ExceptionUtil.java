package com.jdk2010.framework.dal.exception;

import org.springframework.dao.DataAccessException;

public class ExceptionUtil {
    public static void throwException(Exception e) {
        if (e instanceof DataAccessException) {
            throw (DataAccessException) e;
        } else if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        } else {
            throw new RuntimeException(e);
        }

    }
}
