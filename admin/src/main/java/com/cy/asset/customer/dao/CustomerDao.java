package com.cy.asset.customer.dao;

import com.cy.asset.customer.bean.AddressBean;
import com.cy.asset.customer.bean.CustomerBean;
import com.cy.asset.customer.bean.PhoneBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wc
 * @date 2020/12/21
 * @describe 客户Dao
 */
@Repository
public interface CustomerDao {

    /**
     * 保存客户信息
     * **/
    void saveCustomer(List<CustomerBean> customerList);

    /**
     * 保存客户电话信息
     * **/
    void saveCustomerPhone(List<PhoneBean> phoneList);

    /**
     * 保存客户地址信息
     * **/
    void saveCustomerAddress(List<AddressBean> addressList);

}
