package com.cy.asset.task.service;

import com.cy.asset.task.bean.CaseQueryBean;
import com.cy.asset.task.bean.CaseResultBean;

import java.util.List;

/**
 * @author wc
 * @date 2020/12/18
 */
public interface CaseManageService {

    List<CaseResultBean> queryCase(CaseQueryBean caseQuery);

}