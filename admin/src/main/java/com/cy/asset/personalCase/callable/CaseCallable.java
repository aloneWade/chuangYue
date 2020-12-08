package com.cy.asset.personalCase.callable;

import com.cy.asset.personalCase.dao.CaseDao;
import com.cy.asset.personalCase.strategy.CaseStrategy;
import com.cy.asset.personalCase.strategy.GenerateCaseContext;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author wc
 * @date 2020/12/3
 */
public class CaseCallable implements Callable {

    private Integer succeedCount = 0;

    private List<Map<String,Object>> caseMap;

    private String caseType;

    public CaseCallable(List<Map<String,Object>> caseMap, String caseType){
        this.caseMap = caseMap;
        this.caseType = caseType;
    }

    @Override
    public Integer call() {
        try {
            // 策略模式，不同case执行不同策略
            GenerateCaseContext caseContext = new GenerateCaseContext();
            CaseStrategy strategy = caseContext.getCaseStrategy(caseType);
            // 上载成功数统计
            succeedCount = strategy.generateCase(caseMap);
            return succeedCount;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return succeedCount;
        }
    }

}
