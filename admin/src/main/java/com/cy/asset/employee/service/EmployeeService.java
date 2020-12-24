package com.cy.asset.employee.service;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.employee.bean.EmployeeBean;

public interface EmployeeService {

    SuccessResponse queryEmployee(EmployeeBean employee);

    SuccessResponse addEmployee(EmployeeBean employee);

    SuccessResponse updateEmployee(EmployeeBean employee);

}
