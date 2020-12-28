package com.cy.asset.task.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wc
 * @date 2020/12/17
 * @describe 案件信息查询DTO
 */
@Data
public class CaseQueryBean {

    /** 客户姓名 **/
    private String customerName;
    /** 客户号 **/
    private String partyNo;
    /** 地区 **/
    private String provinces;
    /** 电话号码 **/
    private String phoneNo;
    /** 身份证号码 **/
    private String cardId;
    /** 产品 **/
    private String productName;
    /** 手别 **/
    private String collectionPhase;
    /** 组别 **/
    private String teamLeaderUm;
    /** 催收员 **/
    private String um;
    /** 账单日 **/
    private String statementDate;
    /** 催收状态 **/
    private String collectStatus;
    /** 标色状态 **/
    private String colorStatus;
    /** 案件状态 **/
    private String caseStatus;
    /** 工号 **/
    private String empNo;
    /** 职位编码 **/
    private String positionNo;
    /** 部门 **/
    private String deptNo;
    /** 委案金额开始金额 **/
    private BigDecimal appointCaseAmountBegin;
    /** 委案金额结束金额 **/
    private BigDecimal appointCaseAmountEnd;
    /** 当前30DAY开始金额 **/
    private BigDecimal current30DayBegin;
    /** 当前30DAY结束金额 **/
    private BigDecimal current30DayEnd;

}
