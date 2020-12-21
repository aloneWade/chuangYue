package com.cy.asset.task.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wc
 * @date 2020/12/18
 * @describe 案件队列结果DTO
 */
@Data
public class CaseResultBean {

    /** 批次号 **/
    private String batchCode;
    /** 委案日期 **/
    private Date appointCaseDate;
    /** 预计退案日期 **/
    private Date expectBackCaseDate;
    /** 案件号 **/
    private String caseSerialNumber;
    /** 客户号 **/
    private String partyNo;
    /** 项目 **/
    private String client;
    /** 产品 **/
    private String productName;
    /** 客户姓名 **/
    private String customerName;
    /** 地区 **/
    private String provinces;
    /** 逾期天数 **/
    private String overdueDays;
    /** 委案金额 **/
    private BigDecimal appointCaseAmount;
    /** 在催金额 **/
    private BigDecimal afterUpdateAmount;
    /** 跟进人 **/
    private String currentHandler;
    /** 最近跟进时间 **/
    private String lateFollowUpDate;
    /** 催收结果 **/
    private String collectResult;
    /** 催收状态 **/
    private String collectStatus;
    /** 催记内容 **/
    private String recordContent;
    /** 已跟进次数 **/
    private Integer followUpNum;

}
