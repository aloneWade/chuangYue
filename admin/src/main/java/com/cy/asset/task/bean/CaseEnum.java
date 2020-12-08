package com.cy.asset.task.bean;

/**
 * @author wc
 * @date 2020/12/4
 */
public enum CaseEnum {

    PING_AN("pingAn", "平安"),
    MEI_TUAN("meiTuan", "美团"),
    HANG_XIAO("hangXiao", "杭消");

    private String caseType;
    private String caseDescribe;

    CaseEnum(String caseType, String caseDescribe) {
        this.caseType = caseType;
        this.caseDescribe = caseDescribe;
    }

    public String caseType(){
        return this.caseType;
    }

    public String caseDescribe(){
        return this.caseDescribe;
    }

}
