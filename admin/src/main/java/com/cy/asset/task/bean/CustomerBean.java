package com.cy.asset.task.bean;

import com.cy.asset.common.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author wc
 * @date 2020/12/7
 * @describe 客户信息
 */
@Data
public class CustomerBean extends BaseDTO {

    /** 主键 **/
    private String id;

    /** 客户姓名 **/
    private String customerName;
    /** 用户号 **/
    private String partyNo;
    /** 客户性别 **/
    private String customerSex;
    /** 证件号码 **/
    private String cardId;
    /** 手机号 **/
    private String phoneNo;
    /** 公司地址 **/
    private String companyAddress;
    /** 公司电话 **/
    private String companyTel;
    /** 住宅地址 **/
    private String residentialAddress;
    /** 住宅电话 **/
    private String residentialTel;
    /** 省份 **/
    private String provinces;
    /** 城市 **/
    private String city;
    /** 大区 **/
    private String regional;
    /** 邮编 **/
    private String zipCode;
    /** 出生日期 **/
    private Date birthDate;
    /** 年龄 **/
    private String customerAge;

}
