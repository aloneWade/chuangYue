package com.cy.asset.record.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.loginUser.Um;
import com.cy.asset.record.bean.RecordBean;
import com.cy.asset.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wc
 * @date 2020/12/28
 * @describe 催记控制层
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/saveRecord",method = RequestMethod.POST)
    public SuccessResponse saveRecord(@Um String um,
                                      @RequestBody RecordBean record){
        record.setCreatedBy("test");
        return recordService.saveRecord(record);
    }

}
