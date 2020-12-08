package com.cy.asset.common;

/**
 *
 * @author wc
 * @date 2020/11/25
 */
public class BaseMessage {

    private String message;

    public BaseMessage(){

    }

    public BaseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
