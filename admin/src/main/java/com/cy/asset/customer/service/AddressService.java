package com.cy.asset.customer.service;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.customer.bean.AddressBean;

public interface AddressService {

    SuccessResponse queryCustomerAddress(String partyNo, String available);

    SuccessResponse addCustomerAddress(AddressBean addressBean);

}
