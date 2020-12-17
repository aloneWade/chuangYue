package com.cy.asset.task.bean;

import lombok.Data;

/**
 * @author wc
 * @date 2020/12/17
 * @describe 案件信息查询DTO
 */
@Data
public class CaseQueryDTO {

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

}
