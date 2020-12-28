package com.cy.asset.record.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wc
 * @date 2020/12/28
 * @describe 催记Bean
 */
@Data
public class RecordBean {

    /** 主键 **/
    private String id;

    /** 个案序列号 **/
    private String caseSerialNumber;

    /** 联系人 **/
    private String contactName;

    /** 联系电话 **/
    private String contactPhone;

    /** 催收结果 **/
    private String collectResult;

    /** 催收状态 **/
    private String collectStatus;

    /** 是否还款 **/
    private Boolean repayment;

    /** 还款金额 **/
    private BigDecimal repaymentAmount;

    /** 催记备注 **/
    private String remark;

    /** 创建人 **/
    private String createdBy;

}
