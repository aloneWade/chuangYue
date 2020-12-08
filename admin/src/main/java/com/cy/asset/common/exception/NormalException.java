package com.cy.asset.common.exception;

/**
 * 通用异常类
 * @author wc
 * @date 20/11/25
 */
public class NormalException extends RuntimeException{

    /**
     * 无参构造函数.
     */
    public NormalException() {
    }

    /**
     * 有参构造函数.
     *
     * @param s 参数
     */
    public NormalException(String s) {
        super(s);
    }

}
