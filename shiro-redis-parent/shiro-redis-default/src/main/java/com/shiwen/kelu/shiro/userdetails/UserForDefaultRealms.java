package com.shiwen.kelu.shiro.userdetails;

/**
 * 
 * @author zhangkai
 *
 */
public class UserForDefaultRealms extends AbstractUserDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4824960079802506112L;

	public UserForDefaultRealms(final String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public UserForDefaultRealms(String id, String username, String password) {
		this(username, password);
		this.id = id;
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
