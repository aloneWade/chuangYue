package com.cy.asset.task.service;

import com.cy.asset.common.response.SuccessResponse;

/**
 * @author wc
 * @date 2020/12/2
 */
public interface CaseService {

    SuccessResponse importPingAnCase(String filePath);

}
