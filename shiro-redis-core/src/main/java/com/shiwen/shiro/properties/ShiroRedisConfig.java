package com.shiwen.shiro.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = ShiroRedisConfig.SHIRO_REDIS_CONFIG)
public class ShiroRedisConfig {

	public static final String SHIRO_REDIS_CONFIG = "shiro.redis";

	private String host 								= "127.0.0.1";
	private int port 									= 6379;
	private long expire 								= 1800L;
	private long timeout 								= 2 * 1000L;
	private String password;
	private int database 								= 0;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public long getExpire() {
		return expire;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

}
