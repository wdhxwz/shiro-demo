package com.wangdh.shiro.demo.quickstart;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("wang", "321");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
		}

		if(subject.isAuthenticated()){
			System.out.println("login success");
		}else{
			System.out.println("login fail");
		}
		
		subject.logout();
	}
}
