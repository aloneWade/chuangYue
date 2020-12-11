package com.cy.asset.task.strategy;

import com.cy.asset.task.bean.CaseEnum;

/**
 * @author wc
 * @date 2020/12/4
 */
public class GenerateCaseContext implements CaseContext{

    @Override
    public CaseStrategy getCaseStrategy(CaseEnum caseEnum) {
        switch (caseEnum) {
            case PING_AN_LIST:
                return new PingAnCaseStrategy();
            case MEI_TUAN_LIST:
                return new MeiTuanCaseStrategy();
            case HANG_XIAO_LIST:
                return new HangXiaoCaseStrategy();
            default:
                return null;
        }
    }

}
