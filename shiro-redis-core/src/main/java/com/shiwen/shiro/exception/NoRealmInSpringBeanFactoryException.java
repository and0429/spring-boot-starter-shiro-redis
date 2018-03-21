package com.shiwen.shiro.exception;

/**
 * 
 * @author zhangkai
 *
 */
public class NoRealmInSpringBeanFactoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3159854285513086989L;

	public NoRealmInSpringBeanFactoryException() {
	}

	public NoRealmInSpringBeanFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoRealmInSpringBeanFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRealmInSpringBeanFactoryException(String message) {
		super(message);
	}

	public NoRealmInSpringBeanFactoryException(Throwable cause) {
		super(cause);
	}

}
