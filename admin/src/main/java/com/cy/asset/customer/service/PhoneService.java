package com.cy.asset.customer.service;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.customer.bean.PhoneBean;

public interface PhoneService {

    SuccessResponse queryCustomerPhone(String partyNo, String available);

    SuccessResponse addCustomerPhone(PhoneBean phoneBean);

}
