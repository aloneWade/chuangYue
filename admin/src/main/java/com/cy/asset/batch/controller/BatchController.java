package com.cy.asset.batch.controller;

import com.cy.asset.batch.bean.Batch;
import com.cy.asset.batch.service.BatchService;
import com.cy.asset.common.Constants;
import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.loginUser.Um;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wc
 * @date 2020/12/8
 * @describe 批次管理controller
 */
@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @RequestMapping(value = "/addBatch",method = RequestMethod.POST)
    public SuccessResponse addBatch(@Um String um,
                                    @RequestBody Batch batch){
        batch.setCreatedBy(um);
        return batchService.addBatch(batch);
    }

    @RequestMapping(value = "/deleteBatch",method = RequestMethod.POST)
    public SuccessResponse deleteBatch(@Um String um,
                                       @RequestBody Batch batch){
        batch.setUpdatedBy(um);
        return batchService.deleteBatch(batch);
    }

    @RequestMapping(value = "/queryBatchList",method = RequestMethod.POST)
    public SuccessResponse queryBatchList(@RequestBody Batch batch,
                                          @RequestParam(defaultValue = Constants.ONE_STRING) Integer pageNum,
                                          @RequestParam(defaultValue = Constants.TEN_STRING) Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(batchService.queryBatchList(batch));
        return new SuccessResponse(pageInfo);
    }

}
