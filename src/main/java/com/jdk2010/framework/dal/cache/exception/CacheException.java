package com.jdk2010.framework.dal.cache.exception;

/**
 * 缓存异常处理类
 * 
 */
public class CacheException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(Throwable cause) {
		super(cause);
	}

	public CacheException() {
		super();
	}

}
