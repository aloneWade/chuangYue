package com.cy.asset.task.dao;

import com.cy.asset.task.bean.CaseBean;
import com.cy.asset.task.bean.CustomerBean;
import com.cy.asset.task.bean.MeiTuanCase;
import com.cy.asset.task.bean.PingAnCase;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王成
 * @date 2020-12-26
 */
@Repository
public interface CaseDao {

    /**
     * 保存客户信息
     * **/
    void saveCustomer(List<CustomerBean> customerList);

    /**
     * 保存个案信息
     * **/
    void saveCase(List<CaseBean> caseList);

    /**
     * 保存平安案件信息
     * **/
    void savePingAnCase(PingAnCase pingAnCase);

    /**
     * 保存美团案件信息
     * **/
    void saveMeiTuanCase(List<MeiTuanCase> meiTuanList);

}
