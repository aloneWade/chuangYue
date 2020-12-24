package com.cy.asset.employee.service.Impl;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.employee.bean.DeptEnum;
import com.cy.asset.employee.bean.EmployeeBean;
import com.cy.asset.employee.bean.PositionEnum;
import com.cy.asset.employee.dao.EmployeeDao;
import com.cy.asset.employee.service.EmployeeService;
import com.cy.asset.login.bean.Login;
import com.cy.asset.login.dao.LoginDao;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wc
 * @date 2020/12/23
 * @describe 人员信息ServiceImpl
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private LoginDao loginDao;

    @Override
    public SuccessResponse queryEmployee(EmployeeBean employee) {
        return new SuccessResponse(employeeDao.queryEmployee(employee));
    }

    @Override
    public SuccessResponse addEmployee(EmployeeBean employee) {
        // 校验专员UM是否存在
        if(CollectionUtils.isNotEmpty(employeeDao.queryEmployee(employee))){
            logger.info("新增账户失败！" + employee.getDeptNo() + "工号已存在");
            return new SuccessResponse("工号已存在！");
        }
        employee.setPositionName(PositionEnum.getDescribe(employee.getPositionNo()));
        employee.setDeptName(DeptEnum.getDescribe(employee.getDeptNo()));
        // 新增专员
        employeeDao.addEmployee(employee);
        // 初始化密码
        Login login = new Login();
        login.setUsername(employee.getEmpNo());
        login.setPassword("$2a$10$EnsqBPtQ.YPllK7k1hfL2OUKp42BBcf4t1KSTFEkwT1VRbfKbfOFW");
        login.setCreatedBy(employee.getCreatedBy());
        loginDao.addLogin(login);
        return new SuccessResponse("新增账户成功！");
    }

    @Override
    public SuccessResponse updateEmployee(EmployeeBean employee) {
        // 离职账号注销,离职专员不能修改成在职
        if("resign".equals(employee.getPositionStatus())){
            loginDao.deleteLoginUser(employee.getEmpNo());
        }
        employee.setPositionName(PositionEnum.getDescribe(employee.getPositionNo()));
        employee.setDeptName(DeptEnum.getDescribe(employee.getDeptNo()));
        employeeDao.updateEmployee(employee);
        return new SuccessResponse();
    }
}
