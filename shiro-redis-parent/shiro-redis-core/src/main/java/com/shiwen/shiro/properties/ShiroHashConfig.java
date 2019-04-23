package com.shiwen.shiro.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zhangkai
 *
 */
/**
 * @author zhangkai
 *
 */
@Component
@ConfigurationProperties(prefix = ShiroHashConfig.PREFIX)
public class ShiroHashConfig {

	public static final String PREFIX = "shiro.hash";

	/**
	 * 摘要类型
	 */
	private String algorithmName;

	/**
	 * 摘要次数
	 */
	private int hashIterations;

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

}
