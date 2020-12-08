package com.cy.asset.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 禁止访问异常类. 403
 * @author wc
 * @date 20/11/25
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoPermissionException extends RuntimeException {

    /**
     * 无参构造函数.
     */
    public NoPermissionException() {
    }

    /**
     * 有参构造函数.
     *
     * @param s 参数
     */
    public NoPermissionException(String s) {
        super(s);
    }
}
