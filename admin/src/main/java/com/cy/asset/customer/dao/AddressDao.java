package com.cy.asset.customer.dao;

import com.cy.asset.customer.bean.AddressBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wc
 * @date 2020/12/23
 */
@Repository
public interface AddressDao {

    /**
     * 批量保存客户地址信息
     * **/
    void saveCustomerAddress(List<AddressBean> addressList);

    /**
     * 查询客户地址信息
     * **/
    List<AddressBean> queryCustomerAddress(@Param("partyNo") String partyNo, @Param("available") String available);

    /**
     * 新增客户地址信息
     * **/
    void addCustomerAddress(AddressBean addressBean);
}
