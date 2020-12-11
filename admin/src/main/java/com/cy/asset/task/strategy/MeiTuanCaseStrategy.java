package com.cy.asset.task.strategy;

import com.cy.asset.common.util.BeanContext;
import com.cy.asset.common.util.BeanToMapUtil;
import com.cy.asset.task.bean.CaseBean;
import com.cy.asset.task.bean.CustomerBean;
import com.cy.asset.task.bean.MeiTuanCase;
import com.cy.asset.task.bean.ResultBean;
import com.cy.asset.task.dao.CaseDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wc
 * @date 2020/12/4
 */
public class MeiTuanCaseStrategy implements CaseStrategy {

    private CaseDao caseDao = BeanContext.getApplicationContext().getBean(CaseDao.class);;

    @Override
    public ResultBean generateCase(List<Map<String,Object>> caseMap) {
        ResultBean result = new ResultBean();
        // 成功案件数统计
        AtomicReference<Integer> succeedCount = new AtomicReference<>(0);
        // 案件总金额统计
        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(new BigDecimal(0.00));
        List<MeiTuanCase> meiTuanCaseList = BeanToMapUtil.convertListMap2ListBean(caseMap, MeiTuanCase.class);
        List<CustomerBean> customerList = new ArrayList<>();
        List<CaseBean> caseList = new ArrayList<>();
        List<MeiTuanCase> meiTuanList = new ArrayList<>();
        meiTuanCaseList.forEach((meiTuanCase)->{
            CustomerBean customer = new CustomerBean();
            CaseBean caseBean = new CaseBean();
            // 将平安案件信息拷贝到客户和个案的bean属性中
            BeanUtils.copyProperties(meiTuanCase,customer);
            BeanUtils.copyProperties(meiTuanCase,caseBean);
            customerList.add(customer);
            caseList.add(caseBean);
            meiTuanList.add(meiTuanCase);
            // 生成客户信息
            if( customerList.size() == 500 ){
                caseDao.saveCustomer(customerList);
                customerList.clear();
            }
            // 生成个案信息
            if( caseList.size() == 500 ){
                caseDao.saveCase(caseList);
                caseList.clear();
            }
            // 平安案件信息
            if( caseList.size() == 400 ){
                caseDao.saveMeiTuanCase(meiTuanList);
                meiTuanList.clear();
            }
            totalAmount.set(totalAmount.get().add((meiTuanCase.getAppointCaseAmount())));
            succeedCount.getAndSet(succeedCount.get() + 1);
        });
        if(CollectionUtils.isNotEmpty(customerList)){
            caseDao.saveCustomer(customerList);
        }
        if(CollectionUtils.isNotEmpty(caseList)){
            caseDao.saveCase(caseList);
        }
        if(CollectionUtils.isNotEmpty(meiTuanList)){
            caseDao.saveMeiTuanCase(meiTuanList);
        }
        result.setTotalCount(meiTuanCaseList.size());
        result.setSucceedCount(succeedCount.get());
        result.setTotalAmount(totalAmount.get());
        return result;
    }

}
