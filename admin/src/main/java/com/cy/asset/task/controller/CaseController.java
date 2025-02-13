package com.cy.asset.task.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.ExcelUtil;
import com.cy.asset.task.bean.CaseImportBean;
import com.cy.asset.task.bean.HangXiaoCase;
import com.cy.asset.task.bean.MeiTuanCase;
import com.cy.asset.task.bean.PingAnCase;
import com.cy.asset.task.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wc
 * @date 2020/12/2
 * @describe 个人案件controller
 */
@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @RequestMapping(value = "/exportPingAnCaseTemplate",method = RequestMethod.POST)
    public SuccessResponse exportPingAnCaseTemplate(HttpServletResponse response){
        List<PingAnCase> personList = new ArrayList<>();
        //导出操作
        ExcelUtil.exportExcel(personList, null,"平安", PingAnCase.class,"平安.xls",response);
        return new SuccessResponse("导出模板成功");
    }

    @RequestMapping(value = "/exportMeiTuanCaseTemplate",method = RequestMethod.POST)
    public SuccessResponse exportMeiTuanCaseTemplate(HttpServletResponse response){
        List<MeiTuanCase> personList = new ArrayList<>();
        //导出操作
        ExcelUtil.exportExcel(personList,null,"美团", MeiTuanCase.class,"美团.xls",response);
        return new SuccessResponse("导出模板成功");
    }

    @RequestMapping(value = "/exportHangXiaoCaseTemplate",method = RequestMethod.POST)
    public SuccessResponse exportHangXiaoCaseTemplate(HttpServletResponse response){
        List<HangXiaoCase> personList = new ArrayList<>();
        //导出操作
        ExcelUtil.exportExcel(personList,null,"杭消", HangXiaoCase.class,"杭消.xls",response);
        return new SuccessResponse("导出模板成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/importPingAnCase",method = RequestMethod.POST)
    public SuccessResponse importPingAnCase(@Valid @RequestBody CaseImportBean caseImport){
        return caseService.importPingAnCase(caseImport);
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/importMeiTuanCase",method = RequestMethod.POST)
    public SuccessResponse importMeiTuanCase(@Valid @RequestBody CaseImportBean caseImport){
        return caseService.importMeiTuanCase(caseImport);
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/importHangXiaoCase",method = RequestMethod.POST)
    public SuccessResponse importHangXiaoCase(@Valid @RequestBody CaseImportBean caseImport){
        return caseService.importHangXiaoCase(caseImport);
    }

}
