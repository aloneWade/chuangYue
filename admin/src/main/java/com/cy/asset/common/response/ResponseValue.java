package com.cy.asset.common.response;

/**
 * Created by fly on 17/12/05.
 */
public class ResponseValue<T> {

    private static final long serialVersionUID = 4994712878622993977L;

    private T data;
    private String message = "true";
    /**
     * 专门给微服务使用
     */
    private int code = 200;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String isMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
