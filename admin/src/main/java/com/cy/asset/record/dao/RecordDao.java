package com.cy.asset.record.dao;

import com.cy.asset.record.bean.RecordBean;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao {

    void saveRecord(RecordBean record);

}
