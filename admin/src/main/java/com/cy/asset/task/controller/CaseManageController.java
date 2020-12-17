package com.cy.asset.task.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.task.bean.CaseQueryDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wc
 * @date 2020/12/17
 * @describe 案件管理Controller
 */
@RestController
@RequestMapping("/case")
public class CaseManageController {

    @RequestMapping(value = "/queryCase",method = RequestMethod.POST)
    public SuccessResponse queryCase(@RequestBody CaseQueryDTO caseQuery){
        return new SuccessResponse("");
    }

}