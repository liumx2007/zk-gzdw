package com.zzqx.support.utils;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author calvin
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1401593546385403720L;
	
	private String errorCode;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
