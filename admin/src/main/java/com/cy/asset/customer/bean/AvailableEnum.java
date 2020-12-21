package com.cy.asset.customer.bean;

public enum AvailableEnum {

    VALID("valid", "有效"),
    INVALID("invalid", "无效");

    private String availableType;
    private String availableDescribe;

    AvailableEnum(String availableType, String availableDescribe) {
        this.availableType = availableType;
        this.availableDescribe = availableDescribe;
    }

    public String availableType(){
        return this.availableType;
    }

    public String availableDescribe(){
        return this.availableDescribe;
    }

}
