package com.cy.asset.employee.bean;

public enum PositionEnum {

    SEATS("seats", "坐席"),
    DIRECTOR("director", "主管"),
    SYSTEM_ADMIN("systemAdmin", "系统管理员");

    private String positionType;
    private String positionDescribe;

    PositionEnum(String positionType, String positionDescribe) {
        this.positionType = positionType;
        this.positionDescribe = positionDescribe;
    }

    public String positionType(){
        return this.positionType;
    }

    public String positionDescribe(){
        return this.positionDescribe;
    }

}
