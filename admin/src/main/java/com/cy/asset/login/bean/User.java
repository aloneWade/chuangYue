package com.cy.asset.login.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王成
 * @date 2020-11-24
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1697862454883405825L;

    private String id;

    /** 账号及工号 **/
    private String empNo;

    /** 姓名 **/
    private String eName;

    /** 性别 **/
    private String sex;

    /** 专员手机号 **/
    private String phoneNumber;

    /** 职位编码 **/
    private String positionNo;

    /** 职位名称 **/
    private String positionName;

    /** 部门编码 **/
    private String deptNo;

    /** 部门名称 **/
    private String deptName;

    /** 分机号 **/
    private String extensionNumber;

    /** 小手机 **/
    private String smallPhoneNumber;

    /** 角色信息 **/
    private String role;

    /** 权限信息 **/
    private List<String> permissionList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEName() {
        return eName;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getSmallPhoneNumber() {
        return smallPhoneNumber;
    }

    public void setSmallPhoneNumber(String smallPhoneNumber) {
        this.smallPhoneNumber = smallPhoneNumber;
    }

    public String getRole() {
        return role;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id='" + id + '\'' +
                "empNo='" + empNo + '\'' +
                ", eName='" + eName + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", positionNo='" + positionNo + '\'' +
                ", positionName='" + positionName + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                ", extensionNumber='" + extensionNumber + '\'' +
                ", smallPhoneNumber='" + smallPhoneNumber + '\'' +
                ", role=" + role +
                ", permissionList=" + permissionList +
                '}';
    }
}
