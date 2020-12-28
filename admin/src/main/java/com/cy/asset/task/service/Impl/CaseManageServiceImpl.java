package com.cy.asset.task.service.Impl;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.task.bean.CaseBean;
import com.cy.asset.task.bean.CaseManageQueryBean;
import com.cy.asset.task.bean.CaseManageResultBean;
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

    @Override
    public SuccessResponse queryCaseDetails(String caseSerialNumber){
        // 案件信息
        CaseBean caseBean = caseManageDao.queryCaseDetailsByCaseId(caseSerialNumber);
        // 根据案件类型查询详细信息
        return new SuccessResponse();
    }

    @Override
    public SuccessResponse queryCaseManage(CaseManageQueryBean caseManage) {
        List<CaseManageResultBean> caseResultList = caseManageDao.listCaseManageResult(caseManage);
        return new SuccessResponse();
    }

}