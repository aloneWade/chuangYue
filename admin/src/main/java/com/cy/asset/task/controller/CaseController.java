package com.cy.asset.task.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.ExcelUtil;
import com.cy.asset.task.bean.CaseImportDTO;
import com.cy.asset.task.bean.HangXiaoCase;
import com.cy.asset.task.bean.MeiTuanCase;
import com.cy.asset.task.bean.PingAnCase;
import com.cy.asset.task.service.CaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        ExcelUtil.exportExcel(personList,"平安","平安", PingAnCase.class,"平安.xls",response);
        return new SuccessResponse("导出模板成功");
    }

    @RequestMapping(value = "/exportMeiTuanCaseTemplate",method = RequestMethod.POST)
    public SuccessResponse exportMeiTuanCaseTemplate(HttpServletResponse response){
        List<MeiTuanCase> personList = new ArrayList<>();
        //导出操作
        ExcelUtil.exportExcel(personList,"美团","美团", MeiTuanCase.class,"美团.xls",response);
        return new SuccessResponse("导出模板成功");
    }

    @RequestMapping(value = "/exportHangXiaoCaseTemplate",method = RequestMethod.POST)
    public SuccessResponse exportHangXiaoCaseTemplate(HttpServletResponse response){
        List<HangXiaoCase> personList = new ArrayList<>();
        //导出操作
        ExcelUtil.exportExcel(personList,"杭消","杭消", HangXiaoCase.class,"杭消.xls",response);
        return new SuccessResponse("导出模板成功");
    }

    @RequestMapping(value = "/importPingAnCase",method = RequestMethod.POST)
    public SuccessResponse importPingAnCase(@RequestBody CaseImportDTO caseImport){
        return caseService.importPingAnCase(caseImport);
    }

    @RequestMapping(value = "/importMeiTuanCase",method = RequestMethod.POST)
    public SuccessResponse importMeiTuanCase(@RequestBody CaseImportDTO caseImport){
        return caseService.importMeiTuanCase(caseImport);
    }

    @RequestMapping(value = "/importHangXiaoCase",method = RequestMethod.POST)
    public SuccessResponse importHangXiaoCase(@RequestBody CaseImportDTO caseImport){
        return caseService.importHangXiaoCase(caseImport);
    }

}
