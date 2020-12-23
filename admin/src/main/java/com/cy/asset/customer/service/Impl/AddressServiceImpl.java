package com.cy.asset.customer.service.Impl;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.customer.bean.AddressBean;
import com.cy.asset.customer.dao.AddressDao;
import com.cy.asset.customer.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 客户地址ServiceImpl
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    @Override
    public SuccessResponse queryCustomerAddress(String partyNo, String available) {
        return new SuccessResponse(addressDao.queryCustomerAddress(partyNo,available));
    }

    @Override
    public SuccessResponse addCustomerAddress(AddressBean addressBean) {
        addressDao.addCustomerAddress(addressBean);
        return new SuccessResponse();
    }

}
