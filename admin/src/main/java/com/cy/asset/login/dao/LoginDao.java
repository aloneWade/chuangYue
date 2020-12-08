package com.cy.asset.login.dao;

import com.cy.asset.login.bean.Login;
import com.cy.asset.login.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author 王成
 * @date 2020-11-24
 */
@Repository
public interface LoginDao {

    /**
     * 获取登录账号信息
     * @param username
     * @return LoginUser
     */
    Login getLoginUser(String username);

    /**
     * 修改密码
     * @param loginRequest
     */
    void changePassword(Login loginRequest);

    /**
     * 获取登录角色信息
     * @param empNo
     * @return User
     */
    User getEmployeeInfo(String empNo);

}
