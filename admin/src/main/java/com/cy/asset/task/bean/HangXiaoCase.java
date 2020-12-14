package com.cy.asset.task.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.cy.asset.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wc
 * @date 2020/12/2
 * @describe 杭消个案信息
 */
@Data
public class HangXiaoCase extends BaseDTO {

    /** 主键 **/
    private String id;

    @Excel(name = "渠道类型", orderNum = "0")
    private String channelType;
    @Excel(name = "产品简称", orderNum = "1")
    private String productNameAs;
    @Excel(name = "分配机构", orderNum = "2")
    private String assigned;
    @Excel(name = "逾期时段", orderNum = "3")
    private String lateStage;
    @Excel(name = "委案日期", orderNum = "4")
    private Date committeeCaseDate;
    @Excel(name = "退案日期", orderNum = "5")
    private Date cancelDate;
    @Excel(name = "借据号", orderNum = "6")
    private String caseSerialNumber;
    @Excel(name = "客户姓名", orderNum = "7")
    private String customerName;
    @Excel(name = "用户号", orderNum = "8")
    private String userNo;
    @Excel(name = "证件号码", orderNum = "9")
    private String cardId;
    @Excel(name = "贷款余额", orderNum = "10")
    private String loanBalance;
    @Excel(name = "手机号", orderNum = "11")
    private String phoneNo;
    @Excel(name = "委案金额", orderNum = "12")
    private BigDecimal appointCaseAmount;
    @Excel(name = "产品名称", orderNum = "13")
    private String productName;
    @Excel(name = "还款银行", orderNum = "14")
    private String reimbursementBank;
    @Excel(name = "放款金额", orderNum = "15")
    private BigDecimal fixedQuota;
    @Excel(name = "放款期限", orderNum = "16")
    private String loanTimeLimit;
    @Excel(name = "放款时间", orderNum = "17")
    private String loanTime;
    @Excel(name = "逾期天数", orderNum = "18")
    private String overdueDays;
    @Excel(name = "公司名称", orderNum = "19")
    private String companyName;
    @Excel(name = "公司电话", orderNum = "20")
    private String companyTel;
    @Excel(name = "公司地址", orderNum = "21")
    private String companyAddress;
    @Excel(name = "住宅地址", orderNum = "22")
    private String residentialAddress;
    @Excel(name = "配偶姓名", orderNum = "23")
    private String relatives;
    @Excel(name = "配偶手机", orderNum = "24")
    private String relativesTel;
    @Excel(name = "解除逾期状态所需金额", orderNum = "25")
    private BigDecimal overdueAmount;
    @Excel(name = "当前欠款总额", orderNum = "26")
    private BigDecimal currentlyOwedAmount;
    @Excel(name = "当前应扣本金", orderNum = "27")
    private BigDecimal currentlyDeductPrincipal;
    @Excel(name = "合同编号", orderNum = "28")
    private String contractNo;
    @Excel(name = "身份证地址", orderNum = "29")
    private String addressIdCard;
    @Excel(name = "应还款日", orderNum = "30")
    private String dueDate;
    @Excel(name = "应还利息", orderNum = "31")
    private BigDecimal overdueInterest;
    @Excel(name = "应还手续费", orderNum = "32")
    private BigDecimal payableServiceFee;
    @Excel(name = "罚息", orderNum = "33")
    private BigDecimal lateCharge;
    @Excel(name = "月供", orderNum = "34")
    private BigDecimal monthlyPayments;
    @Excel(name = "最大逾期天数", orderNum = "35")
    private String maximumOverdueDays;
    @Excel(name = "最近还款日期", orderNum = "36")
    private String latestPaymentDate;
    @Excel(name = "联系人1姓名", orderNum = "37")
    private String contactPerson1Name;
    @Excel(name = "联系人1电话", orderNum = "38")
    private String contactPhone1;
    @Excel(name = "联系人2姓名", orderNum = "39")
    private String contactPerson2Name;
    @Excel(name = "联系人2电话", orderNum = "40")
    private String contactPhone2;
    @Excel(name = "联系人3姓名", orderNum = "41")
    private String contactPerson3Name;
    @Excel(name = "联系人3电话", orderNum = "42")
    private String contactPhone3;
    @Excel(name = "联系人4姓名", orderNum = "43")
    private String contactPerson4Name;
    @Excel(name = "联系人4电话", orderNum = "44")
    private String contactPhone4;
    @Excel(name = "联系人5姓名", orderNum = "45")
    private String contactPerson5Name;
    @Excel(name = "联系人5电话", orderNum = "46")
    private String contactPhone5;
    @Excel(name = "联系人6姓名", orderNum = "47")
    private String contactPerson6Name;
    @Excel(name = "联系人6电话", orderNum = "48")
    private String contactPhone6;
    @Excel(name = "联系人7姓名", orderNum = "49")
    private String contactPerson7Name;
    @Excel(name = "联系人7电话", orderNum = "50")
    private String contactPhone7;
    @Excel(name = "联系人8姓名", orderNum = "51")
    private String contactPerson8Name;
    @Excel(name = "联系人8电话", orderNum = "52")
    private String contactPhone8;
    @Excel(name = "联系人9姓名", orderNum = "53")
    private String contactPerson9Name;
    @Excel(name = "联系人9电话", orderNum = "54")
    private String contactPhone9;

}
