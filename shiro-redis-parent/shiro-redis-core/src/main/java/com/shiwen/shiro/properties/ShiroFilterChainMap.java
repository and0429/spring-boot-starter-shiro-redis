package com.shiwen.shiro.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * shiro filter chain map
 * 
 * @author and04
 *
 */

@ConfigurationProperties(prefix = ShiroFilterChainMap.PREFIX)
public class ShiroFilterChainMap {

	public static final String PREFIX = "shiro.filter.chain";

	private String map;

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

}
