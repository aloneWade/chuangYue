package com.cy.asset.task.strategy;

import com.cy.asset.task.bean.CaseEnum;

public interface CaseContext {

    CaseStrategy getCaseStrategy(CaseEnum caseEnum);

}
