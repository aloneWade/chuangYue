package com.cy.asset.task.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wc
 * @date 2020/12/10
 * @describe 导入结果DTO
 */
@Data
public class ResultBean {

    /** 成功数 **/
    private Integer succeedCount;
    /** 总条数 **/
    private Integer totalCount;
    /** 总条数 **/
    private BigDecimal totalAmount;

}
