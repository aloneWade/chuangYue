package com.cy.asset.login.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 王成
 * @date 2020/11/24
 * @description
 **/
public class JWTToken implements AuthenticationToken {

    /** 密钥 **/
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
