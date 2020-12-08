package com.cy.asset.personalCase.dao;

import com.cy.asset.personalCase.bean.CaseBean;
import com.cy.asset.personalCase.bean.CustomerBean;
import com.cy.asset.personalCase.bean.PingAnCase;
import org.springframework.stereotype.Repository;

/**
 * @author 王成
 * @date 2020-12-26
 */
@Repository
public interface CaseDao {

    /**
     * 保存客户信息
     * **/
    void saveCustomer(CustomerBean customerBean);

    /**
     * 保存个案信息
     * **/
    void saveCase(CaseBean caseBean);
    /**
     * 保存平安案件信息
     * **/
    void savePingAnCase(PingAnCase PingAnCase);

}
