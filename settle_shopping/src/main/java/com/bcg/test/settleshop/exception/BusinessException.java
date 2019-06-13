package com.bcg.test.settleshop.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
    * @ClassName: BusinessException
    * @Description: Business related exception
    * @author alexlu
    * @date 2019/06/13
    *
 */
public class BusinessException extends RuntimeException {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);

	private static final long serialVersionUID = -5771568110674413033L;

	/**
	 * Error code
	 */
	private Integer code;

	/**
	 * Error message
	 */
	private String msg;

	public BusinessException(int errorCode, String retMsg) {
		super(retMsg);
		this.msg = retMsg;
		this.code = errorCode;
		logger.error(retMsg);
	}

	public BusinessException(int errorCode, String retMsg, String message) {
		super(message);
		this.msg = retMsg;
		this.code = errorCode;
		logger.error(retMsg);
	}
	
	public BusinessException(int errorCode, String retMsg, Throwable cause) {
		super(cause);
		this.msg = retMsg;
		this.code = errorCode;
		logger.error(retMsg);
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.msg = message;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.msg = message;
	}

	public BusinessException(String message) {
		super(message);
		this.msg = message;
	}

	public BusinessException(Throwable cause) {
		super(cause);
		this.msg = cause.getMessage();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

