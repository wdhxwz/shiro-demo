Subject验证的三步骤：
1.收集Subject提交的身份和证明
	UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
	token.setRememberMe(true);
2.向Authentication提交身份和证明
	Subject currentUser = SecurityUtils.getSubject();
	currentUser.login(token);
3.如果提交的内容正确，允许访问，否则重新尝试验证或阻止访问
	if (currentUser.isAuthenticated()) {
	
	}