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

    /** 个案序列号 **/
    private String caseSerialNumber;
    /** 催收状态 **/
    private String collectionStatus;
    /** 固定额度 **/
    private BigDecimal fixedQuota;
    /** 逾期金额 **/
    private BigDecimal overdueAmount;
    /** 逾期天数 **/
    private String overdueDays;
    /** 逾期阶段 **/
    private String lateStage;
    /** 手别 **/
    private String collectionPhase;
    /** 剩余本金 **/
    private BigDecimal remainingPrincipal;
    /** 逾期利息 **/
    private BigDecimal overdueInterest;
    /** 逾期罚息 **/
    private BigDecimal lateCharge;
    /** 撤案日期 **/
    private Date cancelDate;
    /** 账单日 **/
    private Date statementDate;
    /** 还款日 **/
    private String dueDate;
    /** 最近还款日 **/
    private Date latestPaymentDate;
    /** 最近还款金额 **/
    private BigDecimal latestPaymentAmount;
    /** 开卡日 **/
    private Date cardStartDate;
    /** 停卡日 **/
    private Date cardStopDate;
    /** 入催日 **/
    private Date collectionDate;
    /** 整合X DAY **/
    private String concordanceXDay;
    /** 整合30 DAY **/
    private String concordance30Day;
    /** 整合60 DAY **/
    private String concordance60Day;
    /** 案件标识_1 **/
    private String caseIdentify1;
    /** 案件标识_2 **/
    private String caseIdentify2;
    /** 案件标识_3 **/
    private String caseIdentify3;
    /** 备注 **/
    private String remark;
    /** 催收员工号 **/
    private String empNo;
    /** 客户id(身份证) **/
    private String customerId;
    /** 批次号 **/
    private String batchCode;
    /** 产品名称 **/
    private String productName;
    /** 总欠款 **/
    private BigDecimal totalOwed;
    /** 逾期记录 **/
    private String overdueRecord;
    /** 是否分期 **/
    private String whetherInstallment;
    /** 违约金 **/
    private BigDecimal penalSum;
    /** 资金方 **/
    private String fundingParty;
    /** 管制代码 **/
    private String regulationCode;
    /** 最低还款额 **/
    private BigDecimal minimumPayment;
    /** 委案金额 **/
    private BigDecimal appointCaseAmount;

}
