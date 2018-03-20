package com.shiwen.shiro.exception;

public class NotFilterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3159854285513086989L;

	public NotFilterException() {
	}

	public NotFilterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFilterException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFilterException(String message) {
		super(message);
	}

	public NotFilterException(Throwable cause) {
		super(cause);
	}

}
