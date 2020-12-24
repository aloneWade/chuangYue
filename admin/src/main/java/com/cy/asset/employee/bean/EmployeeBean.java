package com.cy.asset.employee.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 员工信息
 */
@Data
public class EmployeeBean {

    /** 主键 **/
    private String id;
    /** 姓名 **/
    @NotNull(message = "员工姓名不能为空")
    private String eName;
    /** 工号 **/
    @NotNull(message = "员工工号不能为空")
    private String empNo;
    /** 性别 **/
    private String sex;
    /** 专员手机号 **/
    private String phoneNumber;
    /** 职位编码 **/
    private String positionNo;
    /** 职位描述 **/
    private String positionName;
    /** 部门编码 **/
    private String deptNo;
    /** 部门名称 **/
    private String deptName;
    /** 部门主管 **/
    private String deptDirector;
    /** 入职时间 **/
    private Date hireDate;
    /** 离职时间 **/
    private Date termDate;
    /** 分机号 **/
    private String extensionNumber;
    /** 小手机 **/
    private String smallPhoneNumber;
    /** 职位状态 onJob在职 resign离职 **/
    private String positionStatus;
    /** 创建人 **/
    private String createdBy;

}
