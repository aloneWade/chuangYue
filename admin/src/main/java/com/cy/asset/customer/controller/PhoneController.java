package com.cy.asset.customer.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.customer.bean.PhoneBean;
import com.cy.asset.customer.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 客户电话Controller
 */
@RestController
@RequestMapping("/customer")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "/queryCustomerPhone",method = RequestMethod.GET)
    public SuccessResponse queryCustomerPhone(@NotNull(message = "客户号不能为空")String partyNo,
                                              String available){
        return phoneService.queryCustomerPhone(partyNo,available);
    }

    @RequestMapping(value = "/addCustomerPhone",method = RequestMethod.POST)
    public SuccessResponse addCustomerPhone(@RequestBody PhoneBean phoneBean){
        return phoneService.addCustomerPhone(phoneBean);
    }

}
