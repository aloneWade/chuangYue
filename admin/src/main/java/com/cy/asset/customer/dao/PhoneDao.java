package com.cy.asset.customer.dao;

import com.cy.asset.customer.bean.PhoneBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wc
 * @date 2020/12/23
 */
@Repository
public interface PhoneDao {

    /**
     * 批量保存客户电话信息
     * **/
    void saveCustomerPhone(List<PhoneBean> phoneList);

    /**
     * 查询客户电话信息
     * **/
    List<PhoneBean> queryCustomerPhone(@Param("partyNo") String partyNo,@Param("available") String available);

    /**
     * 新增客户电话信息
     * **/
    void addCustomerPhone(PhoneBean phoneBean);

}
