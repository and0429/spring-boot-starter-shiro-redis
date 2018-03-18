package com.shiwen.shiro.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ShiroCookieConfig.PREFIX)
public class ShiroCookieConfig {
	public static final String PREFIX = "shiro.cookie";

	private String domain;
	private String path = "/";
	private String sessionIdCookieName;
	private String rememberMeCookieName;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSessionIdCookieName() {
		return sessionIdCookieName;
	}

	public void setSessionIdCookieName(String sessionIdCookieName) {
		this.sessionIdCookieName = sessionIdCookieName;
	}

	public String getRememberMeCookieName() {
		return rememberMeCookieName;
	}

	public void setRememberMeCookieName(String rememberMeCookieName) {
		this.rememberMeCookieName = rememberMeCookieName;
	}

}
