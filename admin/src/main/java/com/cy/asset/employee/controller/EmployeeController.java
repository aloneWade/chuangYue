package com.cy.asset.employee.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.loginUser.Um;
import com.cy.asset.employee.bean.EmployeeBean;
import com.cy.asset.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 人员管理Controller
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/queryEmployee",method = RequestMethod.GET)
    public SuccessResponse queryEmployee(@Um String um,
                                         EmployeeBean employee){
        employee.setCreatedBy(um);
        return employeeService.queryEmployee(employee);
    }

    @RequestMapping(value = "/saveEmployee",method = RequestMethod.POST)
    public SuccessResponse saveEmployee(@Um String um,
                                       @RequestBody EmployeeBean employee){
        employee.setCreatedBy(um);
        return employeeService.saveEmployee(employee);
    }

    @RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
    public SuccessResponse updateEmployee(@Um String um,
                                          @RequestBody EmployeeBean employee){
        employee.setCreatedBy(um);
        return employeeService.updateEmployee(employee);
    }

}
