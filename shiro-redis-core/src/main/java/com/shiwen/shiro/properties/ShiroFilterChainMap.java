package com.shiwen.shiro.properties;

import java.util.LinkedHashMap;

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

	public LinkedHashMap<String, String> map = new LinkedHashMap<>();

	public LinkedHashMap<String, String> getMap() {
		return map;
	}

	public void setMap(LinkedHashMap<String, String> map) {
		this.map = map;
	}

}
