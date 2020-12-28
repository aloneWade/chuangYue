package com.cy.asset.employee.bean;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 职位信息
 */
public enum PositionEnum {

    SEATS("seats", "坐席"),
    TEAM_LEADER("teamLeader", "团队长"),
    DIRECTOR("director", "主管"),
    ABUTMENT("abutment", "对接"),
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

    public static String getDescribe(String type) {
        PositionEnum[] positionEnums = values();
        for (PositionEnum positionEnum : positionEnums) {
            if (positionEnum.positionType().equals(type)) {
                return positionEnum.positionDescribe();
            }
        }
        return null;
    }

    public static String getType(String desc) {
        PositionEnum[] positionEnums = values();
        for (PositionEnum positionEnum : positionEnums) {
            if (positionEnum.positionDescribe().equals(desc)) {
                return positionEnum.positionType();
            }
        }
        return null;
    }

}
