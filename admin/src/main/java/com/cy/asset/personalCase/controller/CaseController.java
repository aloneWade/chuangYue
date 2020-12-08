package com.cy.asset.personalCase.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.ExcelUtil;
import com.cy.asset.personalCase.bean.PingAnCase;
import com.cy.asset.personalCase.service.CaseService;
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

    @RequestMapping(value = "/importPingAnCase",method = RequestMethod.POST)
    public SuccessResponse importPingAnCase(@RequestBody Map<String, Object> map){
        String filePath = (String)map.get("filePath");
        if(StringUtils.isEmpty(filePath)){
            return new SuccessResponse("文件路径为空");
        }
        return caseService.importPingAnCase(filePath);
    }

}
