package com.cy.asset.customer.service.Impl;

import com.cy.asset.common.util.card.StrictDetect;
import com.cy.asset.common.util.card.IdCardInfoExtractor;
import com.cy.asset.customer.bean.CustomerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wc
 * @date 2020/12/15
 * @describe 客户ServiceImpl
 */
@Service
public class CustomerServiceImpl {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomerBean generateCustomerInfo(CustomerBean customer){
        if(!StrictDetect.isValidIdNo(customer.getCardId())){
            logger.info("身份证校验不通过");
        }
        // 通过身份证号获取客户基本信息
        IdCardInfoExtractor idCardInfo = new IdCardInfoExtractor(customer.getCardId());
        customer.setBirthDate(idCardInfo.getBirthday());
        customer.setCity(idCardInfo.getCity());
        customer.setProvinces(idCardInfo.getProvince());
        customer.setRegional(idCardInfo.getRegion());
        customer.setCustomerAge(idCardInfo.getAge());
        customer.setCustomerSex(idCardInfo.getGender());
        return customer;
    }

}
