package com.cy.asset.common;

import lombok.Data;

import java.util.Date;

/**
 * @author wc
 * @date 2020/12/9
 * @describe 基础类
 */
@Data
public class BaseDTO {

    /** 创建人 **/
    private String createdBy;
    /** 创建时间 **/
    private Date createdDate;
    /** 更新人 **/
    private String updatedBy;
    /** 更新时间 **/
    private Date updatedDate;

}
