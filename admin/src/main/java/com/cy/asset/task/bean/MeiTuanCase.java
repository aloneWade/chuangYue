package com.cy.asset.task.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.cy.asset.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wc
 * @date 2020/12/2
 * @describe 美团个案信息
 */
@Data
public class MeiTuanCase extends BaseDTO {

    /** 主键 **/
    private String id;

    @Excel(name = "催收组", orderNum = "0")
    private String collectionOf;
    @Excel(name = "跟进人", orderNum = "1")
    private String followUpPeople;
    @Excel(name = "跟进人Code", orderNum = "2")
    private String followUpPeopleCode;
    @Excel(name = "分案日期", orderNum = "3", importFormat = "yyyy-MM-dd")
    private Date divisionalDate;
    @Excel(name = "到期日", orderNum = "4", importFormat = "yyyy-MM-dd")
    private Date cancelDate;
    @Excel(name = "特殊标识", orderNum = "5")
    private String caseIdentify1;
    @Excel(name = "产品", orderNum = "6")
    private String product;
    @Excel(name = "客户号", orderNum = "7")
    private String caseSerialNumber;
    @Excel(name = "客户姓名", orderNum = "8")
    private String customerName;
    @Excel(name = "身份证号", orderNum = "9")
    private String cardId;
    @Excel(name = "手机号", orderNum = "10")
    private String phoneNo;
    @Excel(name = "联系人姓名", orderNum = "11")
    private String contactName;
    @Excel(name = "联系人手机号", orderNum = "12")
    private String contactPhoneNo;
    @Excel(name = "应还日", orderNum = "13")
    private String dueDate;
    @Excel(name = "逾期借据借款金额", orderNum = "14")
    private BigDecimal theAmountOverdueIOU;
    @Excel(name = "逾期借据笔数", orderNum = "15")
    private String numberOverdueIOU;
    @Excel(name = "逾期借据借款时间", orderNum = "16", importFormat = "yyyy-MM-dd")
    private Date timePastDueOnIOU;
    @Excel(name = "逾期阶段", orderNum = "17")
    private String lateStage;
    @Excel(name = "逾期天数", orderNum = "18")
    private String overdueDays;
    @Excel(name = "逾期金额", orderNum = "19")
    private BigDecimal overdueAmount;
    @Excel(name = "逾期本金", orderNum = "20")
    private BigDecimal withinTimeLimitPrincipal;
    @Excel(name = "逾期利息", orderNum = "21")
    private BigDecimal overdueInterest;
    @Excel(name = "逾期罚息", orderNum = "22")
    private BigDecimal lateCharge;
    @Excel(name = "逾期费用", orderNum = "23")
    private BigDecimal lateDee;
    @Excel(name = "提前结清金额", orderNum = "24")
    private BigDecimal settlementAdvance;
    @Excel(name = "剩余本金", orderNum = "25")
    private BigDecimal appointCaseAmount;
    @Excel(name = "在催金额", orderNum = "26")
    private BigDecimal inTheRushAmount;
    @Excel(name = "低敏电话_未验证", orderNum = "27")
    private String lowTelephoneUnverified;
    @Excel(name = "中敏电话_未验证", orderNum = "28")
    private String mediumTelephoneUnverified;
    @Excel(name = "高敏一级电话_未验证", orderNum = "29")
    private String high1TelephoneUnverified;
    @Excel(name = "低敏地址_未验证", orderNum = "30")
    private String lowAddressUnverified;
    @Excel(name = "中敏地址_未验证", orderNum = "31")
    private String mediumAddressUnverified;
    @Excel(name = "高敏一级地址_未验证", orderNum = "32")
    private String high1AddressUnverified;
    @Excel(name = "poi门店列表", orderNum = "33")
    private String  poiStoresList;
    @Excel(name = "高敏二级电话_未验证", orderNum = "34")
    private String high2TelephoneUnverified;
    @Excel(name = "高敏二级地址_未验证", orderNum = "35")
    private String high2AddressUnverified;
    @Excel(name = "协催授权", orderNum = "36")
    private String urgedAuthorization;
    @Excel(name = "常驻省份", orderNum = "37")
    private String residentProvinces;
    @Excel(name = "免息金额", orderNum = "38")
    private BigDecimal interestFreeAmount;
    @Excel(name = "伪冒标识", orderNum = "39")
    private String caseIdentify2;
    @Excel(name = "催收策略标签", orderNum = "40")
    private String caseIdentify3;

}
