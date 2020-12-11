package com.cy.asset.batch.bean;

import com.cy.asset.common.BaseDTO;
import lombok.Data;
import org.web3j.abi.datatypes.Int;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wc
 * @date 2020/12/8
 * @describe 批次信息
 */
@Data
public class Batch extends BaseDTO {

    /** 批次主键 **/
    private String id;
    /** 批次号 **/
    private String batchCode;
    /** 业务类型 **/
    private String businessType;
    /** 委托方 **/
    private String client;
    /** 产品 **/
    private String productName;
    /** 委托日期 **/
    private Date entrustDate;
    /** 预计退案日期 **/
    private Date expectCaseBackDate;
    /** 备注 **/
    private String remark;
    /** 导入状态 theImported、notImport **/
    private String importStatus;
    /** 户数 **/
    private Integer totalCount;
    /** 总金额 **/
    private BigDecimal totalAmount;

}
