package com.cy.asset.task.strategy;

import com.cy.asset.common.util.BeanContext;
import com.cy.asset.task.bean.ResultBean;
import com.cy.asset.task.dao.CaseDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wc
 * @date 2020/12/4
 */
public class HangXiaoCaseStrategy implements CaseStrategy {

    private CaseDao caseDao = BeanContext.getApplicationContext().getBean(CaseDao.class);;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean generateCase(List<Map<String,Object>> caseMap) {
        ResultBean result = new ResultBean();
        return result;
    }

}
