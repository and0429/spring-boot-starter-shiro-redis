package com.shiwen.kelu.shiro.exception;

/**
 * from spring security
 * 
 * @author zhangkai
 *
 */
public class UsernameNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2689869308930926894L;

	/**
	 * Constructs a <code>UsernameNotFoundException</code> with the specified
	 * message.
	 *
	 * @param msg
	 *            the detail message.
	 */
	public UsernameNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code UsernameNotFoundException} with the specified message and
	 * root cause.
	 *
	 * @param msg
	 *            the detail message.
	 * @param t
	 *            root cause
	 */
	public UsernameNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
