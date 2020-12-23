package com.cy.asset.customer.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.customer.bean.AddressBean;
import com.cy.asset.customer.service.AddressService;
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
 * @describe 客户地址Controller
 */
@RestController
@RequestMapping("/customer")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/queryCustomerAddress",method = RequestMethod.GET)
    public SuccessResponse queryCustomerAddress(@NotNull(message = "客户号不能为空")String partyNo,
                                                String available){
        return addressService.queryCustomerAddress(partyNo,available);
    }

    @RequestMapping(value = "/addCustomerAddress",method = RequestMethod.POST)
    public SuccessResponse addCustomerAddress(@RequestBody AddressBean addressBean){
        return addressService.addCustomerAddress(addressBean);
    }

}
