package com.cy.asset.task.strategy;

import com.cy.asset.common.util.BeanContext;
import com.cy.asset.common.util.BeanToMapUtil;
import com.cy.asset.customer.bean.CustomerBean;
import com.cy.asset.customer.dao.CustomerDao;
import com.cy.asset.task.bean.CaseBean;
import com.cy.asset.task.bean.CaseEnum;
import com.cy.asset.task.bean.CaseImportBean;
import com.cy.asset.task.bean.CollectStatusEnum;
import com.cy.asset.task.bean.HangXiaoCase;
import com.cy.asset.task.bean.ResultBean;
import com.cy.asset.task.dao.CaseDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wc
 * @date 2020/12/4 
 */
public class HangXiaoCaseStrategy implements CaseStrategy {

    private CaseDao caseDao = BeanContext.getApplicationContext().getBean(CaseDao.class);
    private CustomerDao customerDao = BeanContext.getApplicationContext().getBean(CustomerDao.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean generateCase(List<Map<String,Object>> caseMap, CaseImportBean caseImport) {
        ResultBean result = new ResultBean();
        // 成功案件数统计
        AtomicReference<Integer> succeedCount = new AtomicReference<>(0);
        // 案件总金额统计
        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(new BigDecimal(0.00));
        List<HangXiaoCase> hangXiaoCaseList = BeanToMapUtil.convertListMap2ListBean(caseMap, HangXiaoCase.class);
        List<CustomerBean> customerList = new ArrayList<>();
        List<CaseBean> caseList = new ArrayList<>();
        List<HangXiaoCase> hangXiaoList = new ArrayList<>();
        hangXiaoCaseList.forEach((hangXiaoCase)->{
            CustomerBean customer = new CustomerBean();
            CaseBean caseBean = new CaseBean();
            // 将平安案件信息拷贝到客户和个案的bean属性中
            BeanUtils.copyProperties(hangXiaoCase,customer);
            BeanUtils.copyProperties(hangXiaoCase,caseBean);
            caseBean.setBatchCode(caseImport.getBatchCode());
            caseBean.setCollectStatus(CollectStatusEnum.NEW_CASE.collectType());
            caseBean.setCaseSource(CaseEnum.HANG_XIAO.caseType());

            customerList.add(customer);
            caseList.add(caseBean);
            hangXiaoList.add(hangXiaoCase);

            // 生成客户信息
            if( customerList.size() == 500 ){
                customerDao.saveCustomer(customerList);
                customerList.clear();
            }
            // 生成个案信息
            if( caseList.size() == 500 ){
                caseDao.saveCase(caseList);
                caseList.clear();
            }
            // 平安案件信息
            if( caseList.size() == 500 ){
                caseDao.saveHangXiaoCase(hangXiaoList);
                hangXiaoList.clear();
            }
            totalAmount.set(totalAmount.get().add((hangXiaoCase.getAppointCaseAmount())));
            succeedCount.getAndSet(succeedCount.get() + 1);
        });
        if(CollectionUtils.isNotEmpty(customerList)){
            customerDao.saveCustomer(customerList);
        }
        if(CollectionUtils.isNotEmpty(caseList)){
            caseDao.saveCase(caseList);
        }
        if(CollectionUtils.isNotEmpty(hangXiaoList)){
            caseDao.saveHangXiaoCase(hangXiaoList);
        }
        result.setTotalCount(caseMap.size());
        result.setSucceedCount(succeedCount.get());
        result.setTotalAmount(totalAmount.get());
        return result;
    }

}
