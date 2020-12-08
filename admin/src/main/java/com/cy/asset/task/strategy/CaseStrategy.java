package com.cy.asset.task.strategy;

import java.util.List;
import java.util.Map;

public interface CaseStrategy {

    Integer generateCase(List<Map<String,Object>> caseMap);

}
