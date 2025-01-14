package com.cy.asset.task.dao;

import com.cy.asset.task.bean.CaseBean;
import com.cy.asset.task.bean.HangXiaoCase;
import com.cy.asset.task.bean.MeiTuanCase;
import com.cy.asset.task.bean.PingAnCase;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王成
 * @date 2020-12-26
 */
@Repository
public interface CaseDao {

    /**
     * 保存个案信息
     * **/
    void saveCase(List<CaseBean> caseList);

    /**
     * 保存平安案件信息
     * **/
    void savePingAnCase(List<PingAnCase> pingAnCase);

    /**
     * 保存美团案件信息
     * **/
    void saveMeiTuanCase(List<MeiTuanCase> meiTuanList);

    /**
     * 保存杭消案件信息
     * **/
    void saveHangXiaoCase(List<HangXiaoCase> hangXiaoList);

}
