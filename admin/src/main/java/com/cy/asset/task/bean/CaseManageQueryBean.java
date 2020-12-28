package com.cy.asset.task.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author wc
 * @date 2020/12/28
 * @describe 案件管理Bean
 */
@Data
public class CaseManageQueryBean {

    /** 案件开始日期 **/
    private Date caseStartDate;

    /** 案件结束日期 **/
    private Date caseEndDate;

}
