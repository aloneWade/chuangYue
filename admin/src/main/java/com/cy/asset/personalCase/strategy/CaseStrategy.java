package com.cy.asset.personalCase.strategy;

import java.util.List;
import java.util.Map;

public interface CaseStrategy {

    Integer generateCase(List<Map<String,Object>> caseMap);

}
