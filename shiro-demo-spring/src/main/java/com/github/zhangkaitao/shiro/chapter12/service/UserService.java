package com.github.zhangkaitao.shiro.chapter12.service;

import com.github.zhangkaitao.shiro.chapter12.entity.User;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     * 
     * @param user
     */
    public User createUser(User user);

    /**
     * 
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    /**
     * 
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Long userId, Long... roleIds);


    /**
     * 
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * 
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

}
