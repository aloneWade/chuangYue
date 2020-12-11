package com.cy.asset.task.service;

import com.cy.asset.common.response.SuccessResponse;

import java.util.Map;

/**
 * @author wc
 * @date 2020/12/2
 */
public interface CaseService {

    /**
     * 平安
     * @param map
     * @return SuccessResponse
     */
    SuccessResponse importPingAnCase(Map<String, Object> map);

    /**
     * 美团
     * @param map
     * @return SuccessResponse
     */
    SuccessResponse importMeiTuanCase(Map<String, Object> map);

    /**
     * 杭消
     * @param map
     * @return SuccessResponse
     */
    SuccessResponse importHangXiaoCase(Map<String, Object> map);

}
