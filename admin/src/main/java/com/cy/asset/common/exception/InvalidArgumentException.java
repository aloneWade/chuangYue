package com.cy.asset.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 请求参数错误. 返回码 400
 * @author wangcheng
 * @date 20/11/25
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends Exception {

    /**
     * 无参构造方法.
     */
    public InvalidArgumentException() {
    }

    /**
     * 有参构造方法.
     *
     * @param s 参数
     */
    public InvalidArgumentException(String s) {
        super(s);
    }
}
