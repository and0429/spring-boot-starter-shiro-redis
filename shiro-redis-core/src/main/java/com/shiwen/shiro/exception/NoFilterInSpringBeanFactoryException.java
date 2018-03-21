package com.shiwen.shiro.exception;

/**
 * 
 * @author zhangkai
 *
 */
public class NoFilterInSpringBeanFactoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3159854285513086989L;

	public NoFilterInSpringBeanFactoryException() {
	}

	public NoFilterInSpringBeanFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoFilterInSpringBeanFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoFilterInSpringBeanFactoryException(String message) {
		super(message);
	}

	public NoFilterInSpringBeanFactoryException(Throwable cause) {
		super(cause);
	}

}
