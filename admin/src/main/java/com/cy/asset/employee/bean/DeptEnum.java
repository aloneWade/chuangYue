package com.cy.asset.employee.bean;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 部门信息
 */
public enum DeptEnum {

    ping_An("pingAn", "平安"),
    mei_Tuan("meiTuan", "美团");

    private String deptType;
    private String deptDescribe;

    DeptEnum(String deptType, String deptDescribe) {
        this.deptType = deptType;
        this.deptDescribe = deptDescribe;
    }

    public String deptType(){
        return this.deptType;
    }

    public String deptDescribe(){
        return this.deptDescribe;
    }

    public static String getDescribe(String type) {
        DeptEnum[] deptEnums = values();
        for (DeptEnum deptEnum : deptEnums) {
            if (deptEnum.deptType().equals(type)) {
                return deptEnum.deptDescribe();
            }
        }
        return null;
    }

    public static String getType(String desc) {
        DeptEnum[] deptEnums = values();
        for (DeptEnum deptEnum : deptEnums) {
            if (deptEnum.deptDescribe().equals(desc)) {
                return deptEnum.deptType();
            }
        }
        return null;
    }

}
