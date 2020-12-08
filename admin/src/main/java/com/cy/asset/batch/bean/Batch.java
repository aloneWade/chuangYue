package com.cy.asset.batch.bean;

import java.util.Date;

/**
 * @author wc
 * @date 2020/12/8
 * @describe 批次信息
 */
public class Batch {

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
    private Date expectCaseBackData;
    /** 备注 **/
    private String remark;

}
