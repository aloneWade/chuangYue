package com.cy.asset.task.strategy;

/**
 * @author wc
 * @date 2020/12/4
 */
public class GenerateCaseContext implements CaseContext{

    @Override
    public CaseStrategy getCaseStrategy(String caseType) {
        switch (caseType) {
            case "pingAn":
                return new PingAnCaseStrategy();
            default:
                return null;
        }
    }

}
