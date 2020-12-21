package com.cy.asset.task.controller;

import com.cy.asset.common.Constants;
import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.loginUser.LoginUser;
import com.cy.asset.login.bean.User;
import com.cy.asset.task.bean.CaseQueryBean;
import com.cy.asset.task.service.CaseManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wc
 * @date 2020/12/17
 * @describe 案件管理Controller
 */
@RestController
@RequestMapping("/case")
public class CaseManageController {

    @Autowired
    private CaseManageService caseManageService;

    @RequestMapping(value = "/queryCase",method = RequestMethod.GET)
    public SuccessResponse queryCase(CaseQueryBean caseQuery,
                                     @LoginUser User user,
                                     @RequestParam(defaultValue = Constants.ONE_STRING) Integer pageNum,
                                     @RequestParam(defaultValue = Constants.TEN_STRING) Integer pageSize){
        caseQuery.setEmpNo(user.getEmpNo());
        caseQuery.setPositionNo(user.getPositionNo());
        // 分页查询
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(caseManageService.queryCase(caseQuery));
        return new SuccessResponse(pageInfo);
    }

}