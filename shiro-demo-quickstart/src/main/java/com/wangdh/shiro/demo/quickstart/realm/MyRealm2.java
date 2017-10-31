package com.wangdh.shiro.demo.quickstart.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm
 * @author PC
 *
 */
public class MyRealm2 extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		return null;
	}

	/**
	 * 校验登录名密码
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		
		if (!"wang".equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		
		if (!"321".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		
		// 如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
