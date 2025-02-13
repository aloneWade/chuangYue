package com.cy.asset.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 没有权限  401
 * 前端没有附带token 得到401 时,跳到登录界面
 * 带了token  得到401 时, 可能token过期,根据refresh_token 重新获取token
 *
 * @author wangcheng
 * @date 2020-11-24
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    /**
     * 无参构造函数.
     */
    public UnauthorizedException() {
        super();
    }

    /**
     * 有参构造函数.
     *
     * @param message 参数
     */
    public UnauthorizedException(String message) {
        super(message);
    }

}
