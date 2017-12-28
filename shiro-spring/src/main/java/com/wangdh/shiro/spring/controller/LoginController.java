package com.wangdh.shiro.spring.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangdh
 * @Description
 * @date 2017-12-28
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("login")
    public String login(String userName,String password) {
        /*
         * shiro登录方式：根据用户名获取密码，密码为null非法用户；有密码检查是否用户填写的密码
         * 登录成功后无需往httpsession中存放当前用户，这样就跟web容器绑定，关联太紧密；它自己创建
         * subject对象，实现自己的session。这个跟web容器脱离，实现松耦合。
         */

        //调用shiro判断当前用户是否是系统用户
        Subject subject = SecurityUtils.getSubject();   //得到当前用户

        //shiro是将用户录入的登录名和密码（未加密）封装到token对象中
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        try {
            //自动调用AuthRealm.doGetAuthenticationInfo
            subject.login(token);

//            //写seesion，保存当前user对象
//            //从shiro中获取当前用户
//            User curUser = (User) subject.getPrincipal();
//
//            //让懒加载变成立即加载
//            System.out.println(curUser.getDept().getDeptName());
//            Set<Role> roles = curUser.getRoles();
//            for (Role role : roles) {
//                Set<Module> moduless = role.getModules();
//                for (Module m : moduless)
//                    System.out.println(m.getName());
//            }
//
//            //Principal 当前用户对象
//            session.put(SysConstant.CURRENT_USER_INFO, curUser);
            return  "登录成功";
        } catch (Exception ex) {
            ex.printStackTrace();

            return "登录异常";
        }
    }
}
