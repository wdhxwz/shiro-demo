package com.wangdh.shiro.demo.quickstart.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于ini文件配置shiro
 * 
 * @author PC
 *
 */
public class IniConfigApp {
	private static Logger logger = LoggerFactory.getLogger(IniConfigApp.class);

	public static void main(String[] args) {
		Ini ini = new Ini();
		ini.addSection("users").put("wangdh", "123,admin");
		ini.addSection("roles").put("admin", "*");

		Factory<SecurityManager> factory = new IniSecurityManagerFactory(ini);
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);

		Subject currentUser = SecurityUtils.getSubject();
		// 用户名 + 密码
		UsernamePasswordToken token = new UsernamePasswordToken("wangdh", "123");
		token.setRememberMe(true);
		token.setHost("127.0.0.1");
		try {
			Session session = currentUser.getSession();
			if (session == null) {
				System.out.println("no session");
			} else {
				session.setAttribute("name", "wangdh");
			}
			currentUser.login(token);
			session = currentUser.getSession();
			logger.info(currentUser.getSession().getAttribute("name") + "");
		} catch (UnknownAccountException uae) {
			System.out.println("账号不存在");
		} catch (IncorrectCredentialsException ice) {
			System.out.println("密码不正确");
		} catch (LockedAccountException lae) {
			System.out.println("账号已被锁定");
		} catch (AuthenticationException e) {
			System.out.println("验证异常:" + e.getMessage());
		}

		if (currentUser.isAuthenticated()) {
			logger.info("User [" + currentUser.getPrincipal()
					+ "] logged in successfully.");

			// 判断是否拥有某个角色
			if (currentUser.hasRole("admin")) {
				logger.info("has role admin");
			} else {
				logger.info("not an admin user");
			}

			String url = "guest:list";
			// 判断是否拥有权限
			if (currentUser.isPermitted(url)) {
				logger.info("拥有权限:" + url);
			} else {
				logger.info("没有权限:" + url);
			}
		} else {
			System.out.println("login fail");
		}

		// 退出登录后，将session的信息清除掉
		currentUser.logout();
		if (currentUser.getSession() == null) {
			logger.info("session is clean");
		} else {
			logger.info(currentUser.getSession().getAttribute("name") + "");
		}
	}
}