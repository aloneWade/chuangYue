package com.cy.asset.task.service.Impl;

import com.cy.asset.employee.bean.PositionEnum;
import com.cy.asset.task.bean.CaseQueryBean;
import com.cy.asset.task.bean.CaseResultBean;
import com.cy.asset.task.dao.CaseManageDao;
import com.cy.asset.task.service.CaseManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wc
 * @date 2020/12/17
 * @describe 案件管理ServiceImpl
 */
@Service
public class CaseManageServiceImpl implements CaseManageService {

    @Resource
    private CaseManageDao caseManageDao;

    @Override
    public List<CaseResultBean> queryCase(CaseQueryBean caseQuery){
        return caseManageDao.queryCase(caseQuery);
    }

}