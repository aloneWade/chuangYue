package com.cy.asset.record.service.Impl;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.record.bean.RecordBean;
import com.cy.asset.record.dao.RecordDao;
import com.cy.asset.record.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wc
 * @date 2020/12/28
 * @describe 催记Service
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordDao recordDao;

    @Override
    public SuccessResponse saveRecord(RecordBean record) {
        recordDao.saveRecord(record);
        return new SuccessResponse("新增催记成功");
    }

}
