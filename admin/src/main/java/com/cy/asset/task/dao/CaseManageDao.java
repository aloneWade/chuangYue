package com.cy.asset.task.dao;

import com.cy.asset.task.bean.CaseQueryBean;
import com.cy.asset.task.bean.CaseResultBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王成
 * @date 2020-12-26
 */
@Repository
public interface CaseManageDao {

    List<CaseResultBean> queryCase(CaseQueryBean caseQuery);

}
