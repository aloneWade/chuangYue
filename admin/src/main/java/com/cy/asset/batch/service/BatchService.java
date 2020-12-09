package com.cy.asset.batch.service;

import com.cy.asset.batch.bean.Batch;
import com.cy.asset.common.response.SuccessResponse;

import java.util.List;

public interface BatchService {

    SuccessResponse addBatch(Batch batch);

    SuccessResponse deleteBatch(Batch batch);

    List<Batch> queryBatchList(Batch batch);

}
