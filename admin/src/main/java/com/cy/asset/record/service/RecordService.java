package com.cy.asset.record.service;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.record.bean.RecordBean;

public interface RecordService {

    SuccessResponse saveRecord(RecordBean record);

}
