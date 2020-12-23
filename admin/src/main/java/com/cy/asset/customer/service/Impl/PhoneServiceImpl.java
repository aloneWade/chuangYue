package com.cy.asset.customer.service.Impl;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.customer.bean.PhoneBean;
import com.cy.asset.customer.dao.PhoneDao;
import com.cy.asset.customer.service.PhoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 客户电话ServiceImpl
 */
@Service
public class PhoneServiceImpl implements PhoneService {

    @Resource
    private PhoneDao phoneDao;

    @Override
    public SuccessResponse queryCustomerPhone(String partyNo, String available) {
        return new SuccessResponse(phoneDao.queryCustomerPhone(partyNo,available));
    }

    @Override
    public SuccessResponse addCustomerPhone(PhoneBean phoneBean) {
        phoneDao.addCustomerPhone(phoneBean);
        return new SuccessResponse();
    }

}
