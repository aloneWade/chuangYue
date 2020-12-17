package com.cy.asset.task.bean;

/**
 * @author wc
 * @date 2020/12/4
 */
public enum CaseStatusEnum {

    NO_REFUND_CASE("noRefundCase", "未退案"),
    NORMAL("normal", "正常"),
    SUSPENDED("suspended", "暂停"),
    CLOSE_FILE("closeFile", "关档"),
    WITHDRAW_FILE("withdrawFile", "退档");

    private String statusType;
    private String statusDescribe;

    CaseStatusEnum(String statusType, String statusDescribe) {
        this.statusType = statusType;
        this.statusDescribe = statusDescribe;
    }

    public String statusType(){
        return this.statusType;
    }

    public String statusDescribe(){
        return this.statusDescribe;
    }

}
