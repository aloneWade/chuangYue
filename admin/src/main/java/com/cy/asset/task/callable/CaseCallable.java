package com.cy.asset.task.callable;

import com.cy.asset.task.bean.CaseEnum;
import com.cy.asset.task.bean.ResultBean;
import com.cy.asset.task.strategy.CaseStrategy;
import com.cy.asset.task.strategy.GenerateCaseContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author wc
 * @date 2020/12/3
 */
public class CaseCallable implements Callable {

    private ResultBean result = new ResultBean();

    private List<Map<String,Object>> caseMap;

    private CaseEnum caseEnum;

    public CaseCallable(List<Map<String,Object>> caseMap, CaseEnum caseEnum){
        this.caseMap = caseMap;
        this.caseEnum = caseEnum;
    }

    @Override
    public ResultBean call() {
        try {
            // 策略模式，不同case执行不同策略
            GenerateCaseContext caseContext = new GenerateCaseContext();
            CaseStrategy strategy = caseContext.getCaseStrategy(caseEnum);
            // 上载成功数统计
            result = strategy.generateCase(caseMap);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}
