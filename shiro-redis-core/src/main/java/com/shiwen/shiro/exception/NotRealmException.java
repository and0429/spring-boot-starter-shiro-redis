package com.shiwen.shiro.exception;

public class NotRealmException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3159854285513086989L;

	public NotRealmException() {
	}

	public NotRealmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotRealmException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotRealmException(String message) {
		super(message);
	}

	public NotRealmException(Throwable cause) {
		super(cause);
	}

}
