package com.cy.asset.task.bean;

/**
 * @author wc
 * @date 2020/12/16
 */
public enum CollectStatusEnum {

    NEW_CASE("newCase", "新案"),
    COMMIT_REPAYMENT("commitRepayment", "承诺还款"),
    FOCUS_FOLLOW_UP("focusFollowUp", "重点跟进"),
    PAUSE_FOLLOW_UP("pauseFollowUp", "暂停跟进"),
    DAILY_FOLLOW_UP("dailyFollowUp", "每日必须跟进");

    private String collectType;
    private String collectDescribe;

    CollectStatusEnum(String collectType, String collectDescribe) {
        this.collectType = collectType;
        this.collectDescribe = collectDescribe;
    }

    public String collectType(){
        return this.collectType;
    }

    public String collectDescribe(){
        return this.collectDescribe;
    }

}
