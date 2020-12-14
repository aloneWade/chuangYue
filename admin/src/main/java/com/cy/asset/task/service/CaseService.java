package com.cy.asset.task.service;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.task.bean.CaseImportDTO;

import java.util.Map;

/**
 * @author wc
 * @date 2020/12/2
 */
public interface CaseService {

    /**
     * 平安
     * @param caseImport
     * @return SuccessResponse
     */
    SuccessResponse importPingAnCase(CaseImportDTO caseImport);

    /**
     * 美团
     * @param caseImport
     * @return SuccessResponse
     */
    SuccessResponse importMeiTuanCase(CaseImportDTO caseImport);

    /**
     * 杭消
     * @param caseImport
     * @return SuccessResponse
     */
    SuccessResponse importHangXiaoCase(CaseImportDTO caseImport);

}
