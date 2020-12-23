package com.cy.asset.customer.bean;

import lombok.Data;

/**
 * @author wc
 * @date 2020/12/21
 * @describe 客户手机号
 */
@Data
public class PhoneBean {

    /** 客户号 **/
    private String partyNo;
    /** 亲属姓名 **/
    private String name;
    /** 与客户关系 **/
    private String customerRelations;
    /** 类型 **/
    private String phoneType;
    /** 号码 **/
    private String phone;
    /** 有效性 **/
    private String available;
    /** 敏感度 **/
    private String sensitivity;

}
