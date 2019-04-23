package com.shiwen.kelu.shiro.userdetails;

import com.shiwen.kelu.shiro.exception.UsernameNotFoundException;

public interface UserDetailService {

	/**
	 * 
	 * @param username
	 *            用户唯一标识
	 * @return 用户信息
	 */
	UserDetail getByUsername(String username) throws UsernameNotFoundException;

}
