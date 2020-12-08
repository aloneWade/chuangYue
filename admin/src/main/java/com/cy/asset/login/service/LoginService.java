package com.cy.asset.login.service;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.login.bean.Login;

import javax.servlet.http.HttpSession;

/**
 * @author 王成
 */
public interface LoginService {

    /**
     * 获取登录信息
     * @param loginRequest
     * @return SuccessResponse
     */
    SuccessResponse getLoginUser(Login loginRequest, HttpSession httpSession);

    /**
     * 修改密码
     * @param loginRequest
     * @return SuccessResponse
     */
    SuccessResponse changePassword(Login loginRequest);

}
