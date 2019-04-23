package com.shiwen.kelu.shiro.util;

import com.shiwen.kelu.shiro.userdetails.UserForDefaultRealms;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @author zhangkai
 */
public class UserUtil {

    private UserUtil() {
    }

    /**
     * 获取当前登录的用户名
     *
     * @return
     */
    public static String getCurrUsername() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof UserForDefaultRealms) {
            UserForDefaultRealms user = (UserForDefaultRealms) principal;
            return user.getUsername();
        } else
            throw new AuthenticationException();
    }
}
