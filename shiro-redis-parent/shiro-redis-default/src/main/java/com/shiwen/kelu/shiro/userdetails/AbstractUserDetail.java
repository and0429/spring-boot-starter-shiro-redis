package com.shiwen.kelu.shiro.userdetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * UserDetail的适配器
 * 
 * @author zhangkai
 *
 */
public abstract class AbstractUserDetail implements UserDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7999624606247900271L;

	private String username;

	@JsonIgnore
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
