package com.cy.asset.task.bean;

import lombok.Data;

/**
 * @author wc
 * @date 2020/12/28
 * @describe 案件管理
 */
@Data
public class CaseManageResultBean {

    /** 负荷 **/
    private Integer load;

    /** 已跟进 **/
    private Integer followUp;

    /** 覆盖率 **/
    private String coverage;

    /** 地区 **/
    private String area;

    /** 主管 **/
    private String director;

    /** 团队长 **/
    private String teamLeader;

}
