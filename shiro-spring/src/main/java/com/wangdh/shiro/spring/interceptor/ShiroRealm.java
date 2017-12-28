package com.wangdh.shiro.spring.interceptor;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdh
 * @Description
 * @date 2017-12-28
 */
public class ShiroRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        //获取当前用户
        // User user = (User)principals.fromRealm(getName()).iterator().next();

        //得到权限字符串
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // Set<Role> roles = user.getRoles();
        List<String> list = new ArrayList();
        /*for(Role role :roles){
            Set<Module> modules = role.getModules();
            for(Module m:modules){
                if(m.getCtype()==0){
                    //说明是主菜单
                    list.add(m.getCpermission());
                }
            }
        }*/

        info.addStringPermissions(list);
        return info;
    }

    /**
     * 认证&登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;

        String userName = upToken.getUsername();
        // String password = upToken.getPassword();

        // 根据用户名数据库查询用户
        // User user = userService.findUserByName(upToken.getUsername());
        if(!"wangdh".equals(userName)){
            throw  new AuthenticationException("验证不通过");
        }else{
            AuthenticationInfo info = new SimpleAuthenticationInfo(userName, upToken.getPassword(), getName());

            return info;
        }
    }
}
