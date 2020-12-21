package com.cy.asset.customer.bean;

public enum PhoneTypeEnum {

    PHONE("phone", "手机"),
    LANDLINE("landline", "座机");

    private String phoneType;
    private String phoneDescribe;

    PhoneTypeEnum(String phoneType, String phoneDescribe) {
        this.phoneType = phoneType;
        this.phoneDescribe = phoneDescribe;
    }

    public String phoneType(){
        return this.phoneType;
    }

    public String phoneDescribe(){
        return this.phoneDescribe;
    }

}
