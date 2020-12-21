package com.cy.asset.task.service.Impl;

import com.cy.asset.batch.dao.BatchDao;
import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.BeanToMapUtil;
import com.cy.asset.common.util.ExcelUtil;
import com.cy.asset.task.bean.CaseEnum;
import com.cy.asset.task.bean.CaseImportBean;
import com.cy.asset.task.bean.HangXiaoCase;
import com.cy.asset.task.bean.MeiTuanCase;
import com.cy.asset.task.bean.PingAnCase;
import com.cy.asset.task.bean.ResultBean;
import com.cy.asset.task.callable.CaseCallable;
import com.cy.asset.task.service.CaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author 王成
 * @date 2020-11-24
 */
@Service
public class CaseServiceImpl implements CaseService {

    //一个线程处理1000条数据
    private static final Integer COUNT = 1000;

    @Resource
    private BatchDao batchDao;

    @Override
    public SuccessResponse importPingAnCase(CaseImportBean caseImport) {
        List<PingAnCase> caseList = ExcelUtil.importExcel(caseImport.getFilePath(),0,1,PingAnCase.class);
        List<Map<String,Object>> caseMap = BeanToMapUtil.convertListBean2ListMap(caseList,PingAnCase.class);
        caseImport.setCaseEnum(CaseEnum.PING_AN_LIST);
        return this.executorThreadUploadCase(caseMap, caseImport);
    }

    @Override
    public SuccessResponse importMeiTuanCase(CaseImportBean caseImport) {
        List<MeiTuanCase> caseList = ExcelUtil.importExcel(caseImport.getFilePath(),0,1,MeiTuanCase.class);
        List<Map<String,Object>> caseMap = BeanToMapUtil.convertListBean2ListMap(caseList,MeiTuanCase.class);
        caseImport.setCaseEnum(CaseEnum.MEI_TUAN_LIST);
        return this.executorThreadUploadCase(caseMap, caseImport);
    }

    @Override
    public SuccessResponse importHangXiaoCase(CaseImportBean caseImport) {
        List<HangXiaoCase> caseList = ExcelUtil.importExcel(caseImport.getFilePath(),0,1,HangXiaoCase.class);
        List<Map<String,Object>> caseMap = BeanToMapUtil.convertListBean2ListMap(caseList,HangXiaoCase.class);
        caseImport.setCaseEnum(CaseEnum.HANG_XIAO_LIST);
        return this.executorThreadUploadCase(caseMap, caseImport);
    }

    public SuccessResponse executorThreadUploadCase(List<Map<String,Object>> caseMap, CaseImportBean caseImport) {
        ResultBean result = new ResultBean();
        result.setSucceedCount(0);
        result.setTotalAmount(new BigDecimal(0.00));
        result.setTotalCount(0);
        result.setBatchCode(caseImport.getBatchCode());
        // 数据集合大小
        int listSize = caseMap.size();
        // 开启的线程数
        int runSize = (listSize / COUNT) + 1;
        // 存放每个线程的执行数据CaseCallable
        List<Map<String,Object>> newCaseList = null;
        // 线程池
        ExecutorService es = Executors.newFixedThreadPool(10);
        try {
            // 循环创建线程
            for (int i = 0; i < runSize ; i++) {
                // 计算每个线程执行的数据
                int startIndex = (i * COUNT);
                int endIndex;
                if((i + 1) == runSize){
                    endIndex = caseMap.size();
                }else{
                    endIndex = (i + 1) * COUNT;
                }
                newCaseList = caseMap.subList(startIndex, endIndex);
                // 线程类
                CaseCallable caseCallable = new CaseCallable(newCaseList, caseImport);
                // 执行线程获取执行结果
                Future future = es.submit(caseCallable);
                // 统计个线程执行结果
                ResultBean resultCall = (ResultBean)future.get();
                result.setTotalCount(result.getTotalCount() + resultCall.getTotalCount());
                result.setTotalAmount(result.getTotalAmount().add(resultCall.getTotalAmount()));
                result.setSucceedCount(result.getSucceedCount() + resultCall.getSucceedCount());
            }
            // 执行完关闭线程池
            es.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 执行完关闭线程池
            es.shutdown();
        }
        // 更新批次信息，返回结果
        batchDao.updateBatch(result);
        return new SuccessResponse(result);
    }

}
