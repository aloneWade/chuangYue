package com.cy.asset.task.service.Impl;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.common.util.BeanToMapUtil;
import com.cy.asset.common.util.ExcelUtil;
import com.cy.asset.task.bean.CaseEnum;
import com.cy.asset.task.bean.PingAnCase;
import com.cy.asset.task.callable.CaseCallable;
import com.cy.asset.task.service.CaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author 王成
 * @date 2020-11-24
 */
@Service
public class CaseServiceImpl implements CaseService {

    //一个线程处理500条数据
    private static final Integer COUNT = 500;

    @Override
    public SuccessResponse importPingAnCase(String filePath) {
        List<PingAnCase> caseList = ExcelUtil.importExcel(filePath,1,1,PingAnCase.class);
        List<Map<String,Object>> caseMap = BeanToMapUtil.convertListBean2ListMap(caseList,PingAnCase.class);
        return this.executorThreadUploadCase(caseMap, CaseEnum.PING_AN.caseType());
    }

    public SuccessResponse executorThreadUploadCase(List<Map<String,Object>> caseMap,String caseType) {
        Integer succeedCount = 0;
        // 解析excel, titleRows (标题行数)和headerRows (表头行数)
        // 也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        // 数据集合大小
        int listSize = caseMap.size();
        // 开启的线程数
        int runSize = (listSize / COUNT) + 1;
        // 存放每个线程的执行数据CaseCallable
        List<Map<String,Object>> newCaseList = null;
        // 线程池
        ExecutorService es = Executors.newFixedThreadPool(5);
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
                CaseCallable caseCallable = new CaseCallable(newCaseList, caseType);
                // 执行线程获取执行结果
                Future future = es.submit(caseCallable);
                succeedCount = Integer.parseInt(future.get().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 执行完关闭线程池
            es.shutdown();
        }
        return new SuccessResponse(succeedCount);
    }

}
