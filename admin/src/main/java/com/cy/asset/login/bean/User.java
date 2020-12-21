package com.cy.asset.login.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王成
 * @date 2020-11-24
 */
@Data
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

    /** 部门主管 **/
    private String deptDirector;

    /** 分机号 **/
    private String extensionNumber;

    /** 小手机 **/
    private String smallPhoneNumber;

    /** 角色信息 **/
    // private String role;

    /** 权限信息 **/
    // private List<String> permissionList;

}
