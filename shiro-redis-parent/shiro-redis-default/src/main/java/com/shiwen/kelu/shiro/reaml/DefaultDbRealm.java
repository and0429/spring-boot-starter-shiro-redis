package com.shiwen.kelu.shiro.reaml;

import com.shiwen.kelu.shiro.exception.UsernameNotFoundException;
import com.shiwen.kelu.shiro.userdetails.UserDetail;
import com.shiwen.kelu.shiro.userdetails.UserDetailService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的realm
 *
 * @author zhangkai
 */
public class DefaultDbRealm extends AuthorizingRealm {

    public static final Logger logger = LoggerFactory.getLogger(DefaultDbRealm.class);

    /**
     *
     */
    private UserDetailService userdetailsService;

    public DefaultDbRealm(UserDetailService userdetailsService) {
        this.userdetailsService = userdetailsService;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#supports(org.apache.shiro.authc.
     * AuthenticationToken)
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /*
     * 授权 (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.
     * shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * 认证 (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache
     * .shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken ut = (UsernamePasswordToken) token;
        String username = ut.getUsername();
        try {
            UserDetail user = userdetailsService.getByUsername(username);
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        } catch (UsernameNotFoundException e) {
            throw new UnknownAccountException(username, e);
        }
    }

}
