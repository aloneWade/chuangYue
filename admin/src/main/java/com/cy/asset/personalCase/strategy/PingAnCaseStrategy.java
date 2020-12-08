package com.cy.asset.personalCase.strategy;

import com.cy.asset.common.util.BeanContext;
import com.cy.asset.common.util.BeanToMapUtil;
import com.cy.asset.personalCase.bean.CaseBean;
import com.cy.asset.personalCase.bean.CustomerBean;
import com.cy.asset.personalCase.bean.PingAnCase;
import com.cy.asset.personalCase.dao.CaseDao;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wc
 * @date 2020/12/4
 */
public class PingAnCaseStrategy implements CaseStrategy {

    private CaseDao caseDao = BeanContext.getApplicationContext().getBean(CaseDao.class);;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer generateCase(List<Map<String,Object>> caseMap) {
        // 成功案件数统计
        Integer succeedCount = 0;
        List<PingAnCase> pingAnCaseList = BeanToMapUtil.convertListMap2ListBean(caseMap, PingAnCase.class);
        for(PingAnCase pingAnCase : pingAnCaseList){
            CustomerBean customer = new CustomerBean();
            CaseBean caseBean = new CaseBean();
            // 将平安案件信息拷贝到客户和个案的bean属性中
            BeanUtils.copyProperties(pingAnCase,customer);
            BeanUtils.copyProperties(pingAnCase,caseBean);
            // 生成客户信息
            caseDao.saveCustomer(customer);
            // 生成个案信息
            caseDao.saveCase(caseBean);
            // 平安案件信息
            caseDao.savePingAnCase(pingAnCase);
            // case成功数+1
            succeedCount++;
        }
        return succeedCount;
    }

}
