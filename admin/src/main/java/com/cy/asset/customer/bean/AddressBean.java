package com.cy.asset.customer.bean;

import lombok.Data;

/**
 * @author wc
 * @date 2020/12/21
 * @describe 客户地址
 */
@Data
public class AddressBean {

    /** 客户号 **/
    private String partyNo;
    /** 类型 **/
    private String addressType;
    /** 地址 **/
    private String address;
    /** 有效性 **/
    private String available;
    /** 敏感度 **/
    private String sensitive;

}
