package com.cy.asset.batch.dao;

import com.cy.asset.batch.bean.Batch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王成
 * @date 2020-12-26
 */
@Repository
public interface BatchDao {

    /**
     * 新增批次
     * **/
    void addBatch(Batch batch);

    /**
     * 删除批次
     * **/
    void deleteBatch(Batch batch);
    /**
     * 查询批次信息
     * **/
    List<Batch> queryBatchList(Batch batch);

}
