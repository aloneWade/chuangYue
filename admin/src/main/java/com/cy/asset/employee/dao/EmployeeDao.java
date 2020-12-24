package com.cy.asset.employee.dao;

import com.cy.asset.employee.bean.EmployeeBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    List<EmployeeBean> queryEmployee(EmployeeBean employee);

    void addEmployee(EmployeeBean employee);

    void updateEmployee(EmployeeBean employee);

}
