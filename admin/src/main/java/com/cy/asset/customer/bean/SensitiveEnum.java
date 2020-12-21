package com.cy.asset.customer.bean;

public enum SensitiveEnum {

    LOW_SENSITIVE("lowSensitive", "低敏"),
    CENTRE_SENSITIVE("centreSensitive", "中敏"),
    HIGH_SENSITIVE("highSensitive", "高敏");

    private String sensitiveType;
    private String sensitiveDescribe;

    SensitiveEnum(String sensitiveType, String sensitiveDescribe) {
        this.sensitiveType = sensitiveType;
        this.sensitiveDescribe = sensitiveDescribe;
    }

    public String sensitiveType(){
        return this.sensitiveType;
    }

    public String sensitiveDescribe(){
        return this.sensitiveDescribe;
    }

}
