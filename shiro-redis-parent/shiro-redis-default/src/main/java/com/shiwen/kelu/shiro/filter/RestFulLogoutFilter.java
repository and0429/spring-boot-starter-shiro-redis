package com.shiwen.kelu.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhangkai
 *
 */
public class RestFulLogoutFilter extends LogoutFilter {

	public static final Logger logger = LoggerFactory.getLogger(RestFulLogoutFilter.class);

	/**
	 * 
	 */
	@Override
	protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("=========================logout=========================");
	}

}
