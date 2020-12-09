package com.cy.asset.batch.service.Impl;

import com.cy.asset.batch.bean.Batch;
import com.cy.asset.batch.dao.BatchDao;
import com.cy.asset.batch.service.BatchService;
import com.cy.asset.common.response.SuccessResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wc
 * @date 2020/12/8
 * @describe 批次管理service
 */
@Service
public class BatchServiceImpl implements BatchService {

    @Resource
    private BatchDao batchDao;

    public SuccessResponse addBatch(Batch batch){
        batchDao.addBatch(batch);
        return new SuccessResponse("新增批次成功");
    }

    public SuccessResponse deleteBatch(Batch batch){
        // 已导入批次需先删除案件
        batchDao.deleteBatch(batch);
        return new SuccessResponse("删除批次成功");
    }

    public List<Batch> queryBatchList(Batch batch){
        return batchDao.queryBatchList(batch);
    }

}
