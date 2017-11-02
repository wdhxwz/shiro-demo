package com.wangdh.shiro.demo.quickstart;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class LoginLogoutTest {
	@Test
	public void testHelloworld() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		token.setRememberMe(true);

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (UnknownAccountException uae) {
			System.out.println("账号不存在");
		} catch (IncorrectCredentialsException ice) {
			System.out.println("密码不正确");
		} catch (LockedAccountException lae) {
			System.out.println("账号被锁定");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("User [" + currentUser.getPrincipal()
				+ "] logged in successfully.");

		// 6、退出
		subject.logout();
	}

	@Test
	public void testJDBCRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-jdbc-realm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("wl", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}

	@After
	public void tearDown() throws Exception {
		// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
		ThreadContext.unbindSubject();
	}
}
