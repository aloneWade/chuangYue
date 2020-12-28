package com.cy.asset.task.service;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.task.bean.CaseManageQueryBean;
import com.cy.asset.task.bean.CaseQueryBean;
import com.cy.asset.task.bean.CaseResultBean;

import java.util.List;

/**
 * @author wc
 * @date 2020/12/18
 */
public interface CaseManageService {

    List<CaseResultBean> queryCase(CaseQueryBean caseQuery);

    SuccessResponse queryCaseDetails(String caseSerialNumber);

    SuccessResponse queryCaseManage(CaseManageQueryBean caseManage);

}
