package com.cy.asset.task.strategy;

import com.cy.asset.task.bean.CaseImportDTO;
import com.cy.asset.task.bean.ResultBean;

import java.util.List;
import java.util.Map;

public interface CaseStrategy {

    ResultBean generateCase(List<Map<String,Object>> caseMap, CaseImportDTO caseImport);

}
