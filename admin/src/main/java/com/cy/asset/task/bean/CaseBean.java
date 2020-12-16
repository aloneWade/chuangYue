package com.cy.asset.task.bean;

import com.cy.asset.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wc
 * @date 2020/12/4
 * @describe 案件信息
 */
@Data
public class CaseBean extends BaseDTO {

    /** 主键 **/
    private String id;

    /** 批次号 **/
    private String batchCode;
    /** 委案日期 **/
    private Date appointCaseDate;
    /** 预计退案日期 **/
    private Date expectBackCaseDate;
    /** 个案序列号 **/
    private String caseSerialNumber;
    /** 客户号 **/
    private String partyNo;
    /** 逾期天数 **/
    private String overdueDays;
    /** 委案金额 **/
    private BigDecimal appointCaseAmount;
    /** 更新后金额 **/
    private BigDecimal afterUpdateAmount;
    /** 跟进人工号 **/
    private String empNo;
    /** 催收状态 **/
    private String collectStatus;
    /** 标色状态 **/
    private String colorStatus;
    /** 案件状态 **/
    private String caseStatus;
    /** 账单日 **/
    private String statementDate;
    /** 手别 **/
    private String collectionPhase;

}
