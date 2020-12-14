package com.cy.asset.task.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.cy.asset.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wc
 * @date 2020/12/2
 * @describe 平安个案信息
 */
@Data
public class PingAnCase extends BaseDTO {

    /** 主键 **/
    private String id;

    @Excel(name = "手别", orderNum = "0")
    private String collectionPhase;
    @Excel(name = "委托批次", orderNum = "1")
    private String entrustBatch;
    @Excel(name = "委外大区", orderNum = "2")
    private String provinces;
    @Excel(name = "BLK(卡片逾期阶段代号)", orderNum = "3")
    private String regulationCode;
    @Excel(name = "长委次数", orderNum = "4")
    private String longCommitteeNum;
    @Excel(name = "Acode日期", orderNum = "5")
    private String acodeDate;
    @Excel(name = "挂A天数", orderNum = "6")
    private String hangADays;
    @Excel(name = "委外起始日期", orderNum = "7")
    private String outsourcingStartDate;
    @Excel(name = "委外计划截止日期", orderNum = "8")
    private String deadlineOutsourcingProjects;
    @Excel(name = "客户号", orderNum = "9")
    private String customerNumber;
    @Excel(name = "真账号", orderNum = "10")
    private String caseSerialNumber;
    @Excel(name = "首张激活卡号", orderNum = "11")
    private String cardNum1;
    @Excel(name = "卡号2", orderNum = "12")
    private String cardNum2;
    @Excel(name = "首张激活卡号末四位", orderNum = "13")
    private String cardNum1EndFour;
    @Excel(name = "整合X DAY", orderNum = "14")
    private String integrationXDay;
    @Excel(name = "整合30 DAY", orderNum = "15")
    private String integration30Day;
    @Excel(name = "整合60 DAY", orderNum = "16")
    private String integration60Day;
    @Excel(name = "整合90 DAY", orderNum = "17")
    private String integration90Day;
    @Excel(name = "整合120 DAY", orderNum = "18")
    private String integration120Day;
    @Excel(name = "整合150 DAY", orderNum = "19")
    private String integration150Day;
    @Excel(name = "整合180 DAY", orderNum = "20")
    private String integration180Day;
    @Excel(name = "整合210 DAY", orderNum = "21")
    private String integration210Day;
    @Excel(name = "账户余额本金", orderNum = "22")
    private String accountBalancePrincipal;
    @Excel(name = "委案金额AR", orderNum = "23")
    private BigDecimal appointCaseAmount;
    @Excel(name = "分期未入账金额", orderNum = "24")
    private String instalmentUnrecordedAmount;
    @Excel(name = "人民币余额", orderNum = "25")
    private BigDecimal balanceRMB;
    @Excel(name = "人民币最低应缴款金额", orderNum = "26")
    private BigDecimal minimumRMBPayable;
    @Excel(name = "人民币当前CD值", orderNum = "27")
    private String currentRMBCD;
    @Excel(name = "美元余额", orderNum = "28")
    private String balancesDollar;
    @Excel(name = "美元最低应缴款金额", orderNum = "29")
    private String minimumDollarPayable;
    @Excel(name = "美元当前CD值", orderNum = "30")
    private String currentDollarCD;
    @Excel(name = "人民币额度固定额度", orderNum = "31")
    private BigDecimal fixedQuota;
    @Excel(name = "账单日", orderNum = "32")
    private String statementDate;
    @Excel(name = "入催日", orderNum = "33")
    private String inRushDate;
    @Excel(name = "开户日", orderNum = "34")
    private String openAccountDate;
    @Excel(name = "停卡日", orderNum = "35")
    private String stopCardDate;
    @Excel(name = "美元最后一次缴款日", orderNum = "36")
    private String dollarLastPaymentDate;
    @Excel(name = "美元最后一次缴款金额", orderNum = "37")
    private String dollarLastPaymentAmount ;
    @Excel(name = "美元还款日还款笔数", orderNum = "38")
    private String dollarPaymentDateFrequency ;
    @Excel(name = "人民币最后一次缴款日", orderNum = "39")
    private String latestPaymentDate;
    @Excel(name = "人民币last缴款金额", orderNum = "40")
    private	 BigDecimal latestPaymentAmount	;
    @Excel(name = "人民币最后还款笔数", orderNum = "41")
    private String rmbPaymentDateFrequency;
    @Excel(name = "客户姓名", orderNum = "42")
    private String customerName;
    @Excel(name = "客户性别", orderNum = "43")
    private String customerSex;
    @Excel(name = "证件号码", orderNum = "44")
    private String cardId;
    @Excel(name = "客户手机号码", orderNum = "45")
    private String phoneNo;
    @Excel(name = "客户家庭电话", orderNum = "46")
    private String residentialTel;
    @Excel(name = "客户家庭地址", orderNum = "47")
    private String residentialAddress;
    @Excel(name = "客户家庭地址邮编", orderNum = "48")
    private String customerAddressZipCode;
    @Excel(name = "客户单位地址电话", orderNum = "49")
    private String companyTel;
    @Excel(name = "客户单位名称", orderNum = "50")
    private String nameClient;
    @Excel(name = "客户单位地址", orderNum = "51")
    private String customerUnitAddress;
    @Excel(name = "单位邮编", orderNum = "52")
    private String unitZipCode;
    @Excel(name = "电邮", orderNum = "53")
    private String zipCode;
    @Excel(name = "职务", orderNum = "54")
    private String position;
    @Excel(name = "逾期天数", orderNum = "55")
    private String overdueDays;
    @Excel(name = "城市", orderNum = "56")
    private String city;
    @Excel(name = "出生日期", orderNum = "57")
    private String birthDate;
    @Excel(name = "帐单地址邮编", orderNum = "58")
    private String billingAddressZipCode;
    @Excel(name = "帐单地址", orderNum = "59")
    private String billingAddress;
    @Excel(name = "直系亲属联系人姓名", orderNum = "60")
    private String immediateFamilyContactName;
    @Excel(name = "直系人与持卡人关系", orderNum = "61")
    private String cardholderRelationship;
    @Excel(name = "直系人电话", orderNum = "62")
    private String directLine;
    @Excel(name = "直系人手机", orderNum = "63")
    private String directLinePhone;
    @Excel(name = "非直系人姓名", orderNum = "64")
    private String nonLinealPersonName;
    @Excel(name = "非直系人电话", orderNum = "65")
    private String nonLinealPersonTelephone;
    @Excel(name = "非直系人手机", orderNum = "66")
    private String nonLinealPersonPhone;
    @Excel(name = "卡持卡人1客户号", orderNum = "67")
    private String cardholder1CustomerNumber;
    @Excel(name = "卡持卡人1姓名", orderNum = "68")
    private String cardholder1Name;
    @Excel(name = "卡持卡人1手机", orderNum = "69")
    private String cardholder1Phone;
    @Excel(name = "卡持卡人1公司电话", orderNum = "70")
    private String cardholder1CompanyTel;
    @Excel(name = "卡持卡人1家庭电话", orderNum = "71")
    private String cardholder1HomeTel;
    @Excel(name = "卡持卡人2客户号", orderNum = "72")
    private String cardholder2CustomerNumber;
    @Excel(name = "卡持卡人2姓名", orderNum = "73")
    private String cardholder2Name;
    @Excel(name = "卡持卡人2手机", orderNum = "74")
    private String cardholder2Phone;
    @Excel(name = "卡持卡人2公司电话", orderNum = "75")
    private String cardholder2CompanyTel;
    @Excel(name = "卡持卡人2家庭电话", orderNum = "76")
    private String cardholder2HomeTel;
    @Excel(name = "卡持卡人3客户号", orderNum = "77")
    private String cardholder3CustomerNumber;
    @Excel(name = "卡持卡人3姓名", orderNum = "78")
    private String cardholder3Name;
    @Excel(name = "卡持卡人3手机", orderNum = "79")
    private String cardholder3Phone;
    @Excel(name = "卡持卡人3公司电话", orderNum = "80")
    private String cardholder3CompanyTel;
    @Excel(name = "卡持卡人3家庭电话", orderNum = "81")
    private String cardholder3HomeTel;
    @Excel(name = "卡持卡人4客户号", orderNum = "82")
    private String cardholder4CustomerNumber;
    @Excel(name = "卡持卡人4姓名", orderNum = "83")
    private String cardholder4Name;
    @Excel(name = "卡持卡人4手机", orderNum = "84")
    private String cardholder4Phone;
    @Excel(name = "卡持卡人4公司电话", orderNum = "85")
    private String cardholder4CompanyTel;
    @Excel(name = "卡持卡人4家庭电话", orderNum = "86")
    private String cardholder4HomeTel;
    @Excel(name = "卡持卡人5客户号", orderNum = "87")
    private String cardholder5CustomerNumber;
    @Excel(name = "卡持卡人5姓名", orderNum = "88")
    private String cardholder5Name;
    @Excel(name = "卡持卡人5手机", orderNum = "89")
    private String cardholder5Phone;
    @Excel(name = "卡持卡人5公司电话", orderNum = "90")
    private String cardholder5CompanyTel;
    @Excel(name = "卡持卡人5家庭电话", orderNum = "91")
    private String cardholder5HomeTel;
    @Excel(name = "卡持卡人6客户号", orderNum = "92")
    private String cardholder6CustomerNumber;
    @Excel(name = "卡持卡人6姓名", orderNum = "93")
    private String cardholder6Name;
    @Excel(name = "卡持卡人6手机", orderNum = "94")
    private String cardholder6Phone;
    @Excel(name = "卡持卡人6公司电话", orderNum = "95")
    private String cardholder6CompanyTel;
    @Excel(name = "卡持卡人6家庭电话", orderNum = "96")
    private String cardholder6HomeTel;
    @Excel(name = "卡持卡人7客户号", orderNum = "97")
    private String cardholder7CustomerNumber;
    @Excel(name = "卡持卡人7姓名", orderNum = "98")
    private String cardholder7Name;
    @Excel(name = "卡持卡人7手机", orderNum = "99")
    private String cardholder7Phone;
    @Excel(name = "卡持卡人7公司电话", orderNum = "100")
    private String cardholder7CompanyTel;
    @Excel(name = "卡持卡人7家庭电话", orderNum = "101")
    private String cardholder7HomeTel;
    @Excel(name = "卡持卡人8客户号", orderNum = "102")
    private String cardholder8CustomerNumber;
    @Excel(name = "卡持卡人8姓名", orderNum = "103")
    private String cardholder8Name;
    @Excel(name = "卡持卡人8手机", orderNum = "104")
    private String cardholder8Phone;
    @Excel(name = "卡持卡人8公司电话", orderNum = "105")
    private String cardholder8CompanyTel;
    @Excel(name = "卡持卡人8家庭电话", orderNum = "106")
    private String cardholder8HomeTel;
    @Excel(name = "特殊案件类型", orderNum = "107")
    private String specialCaseType;
    @Excel(name = "特殊案件备注", orderNum = "108")
    private String specialCaseRemark;
    @Excel(name = "联系方式", orderNum = "109")
    private String contact;
    @Excel(name = "户籍地址邮编", orderNum = "110")
    private String householdAddressZipCode;
    @Excel(name = "户籍地址", orderNum = "111")
    private String householdAddress;
    @Excel(name = "新增地址", orderNum = "112")
    private String newAddress;
    @Excel(name = "敏感客群", orderNum = "113")
    private String sensitiveCustomers;
    @Excel(name = "手别代码", orderNum = "114")
    private String collectionPhaseCode;
    @Excel(name = "备注1", orderNum = "115")
    private String remark1;
    @Excel(name = "备注2", orderNum = "116")
    private String remark2;
    @Excel(name = "备注3", orderNum = "117")
    private String remark3;
    @Excel(name = "备注4", orderNum = "118")
    private String remark4;
    @Excel(name = "备注5", orderNum = "119")
    private String remark5;
    @Excel(name = "备注6", orderNum = "120")
    private String remark6;
    @Excel(name = "备注7", orderNum = "121")
    private String remark7;
    @Excel(name = "备注8", orderNum = "122")
    private String remark8;
    @Excel(name = "备注9", orderNum = "123")
    private String remark9;
    @Excel(name = "备注10", orderNum = "124")
    private String remark10;
    @Excel(name = "方案1还款额", orderNum = "125")
    private String reimbursementOption1;
    @Excel(name = "方案1减免额", orderNum = "126")
    private String reductionOption1;
    @Excel(name = "方案2还款额", orderNum = "127")
    private String reimbursementOption2;
    @Excel(name = "方案2减免额", orderNum = "128")
    private String reductionOption2;
    @Excel(name = "方案3还款额", orderNum = "129")
    private String reimbursementOption3;
    @Excel(name = "方案3减免额", orderNum = "130")
    private String reductionOption3;
    @Excel(name = "付款起始日", orderNum = "131")
    private String paymentStartDate;
    @Excel(name = "是否免材料", orderNum = "132")
    private String whetherFreeMaterials;
    @Excel(name = "委托起始日", orderNum = "133")
    private String commissioningStartDate;
    @Excel(name = "委托截止日", orderNum = "134")
    private String delegationDeadline;
    @Excel(name = "观测", orderNum = "135")
    private String observation;
    @Excel(name = "债务类型", orderNum = "136")
    private String debtType;
    @Excel(name = "最近24个月逾期情况", orderNum = "137")
    private String last24MonthsOverdue;
    @Excel(name = "人民币可用额度", orderNum = "138")
    private String availableQuotRMB;
    @Excel(name = "美元可用额度", orderNum = "139")
    private String availableQuotDollar;
    @Excel(name = "申请延期次数", orderNum = "140")
    private String extensionNumberApplications;
    @Excel(name = "特殊地区判定", orderNum = "141")
    private String specialAreaDetermination;
    @Excel(name = "是否存在分期未入账", orderNum = "142")
    private String unrecordedInstalment;
    @Excel(name = "证件类型", orderNum = "143")
    private String documentType;
    @Excel(name = "QQ", orderNum = "144")
    private String qq;
    @Excel(name = "省份", orderNum = "145")
    private String provinces1;
    @Excel(name = "城市", orderNum = "146")
    private String city1;
    @Excel(name = "区县", orderNum = "147")
    private String county;
    @Excel(name = "币种", orderNum = "148")
    private String currency;
    @Excel(name = "是否主副卡", orderNum = "149")
    private String primarySecondaryCard;
    @Excel(name = "副卡卡人", orderNum = "150")
    private String deputyCardPeople;
    @Excel(name = "催收员ID", orderNum = "151")
    private String collectionMemberId;
    @Excel(name = "对账单地址", orderNum = "152")
    private String statementAddress;
    @Excel(name = "对账单邮编", orderNum = "153")
    private String statementZipCode;
    @Excel(name = "档案号", orderNum = "154")
    private String fileNo;
    @Excel(name = "账号", orderNum = "155")
    private String account;
    @Excel(name = "逾期日", orderNum = "156")
    private String limitDate;
    @Excel(name = "逾期期数", orderNum = "157")
    private String limitPeriods;
    @Excel(name = "逾期天数", orderNum = "158")
    private String limitDays;
    @Excel(name = "禁止减免", orderNum = "159")
    private String banBreaks;
    @Excel(name = "超限标识", orderNum = "160")
    private String transfiniteLogo;
    @Excel(name = "六个月内作过分期", orderNum = "161")
    private String whetherInstallment;

}
