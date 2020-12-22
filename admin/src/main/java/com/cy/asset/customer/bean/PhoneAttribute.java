package com.cy.asset.customer.bean;

import lombok.Data;

/**
 * @author wc
 * @date 2020/12/22
 * @describe 美团电话信息字段属性
 */
@Data
public class PhoneAttribute {

    /** 亲属姓名 **/
    private String name;
    /** 号码 **/
    private String phone;
    /** 类型 **/
    private String phoneType;

}
