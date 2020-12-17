package com.cy.asset.task.bean;

/**
 * @author wc
 * @date 2020/12/16
 */
public enum ColorStatusEnum {

    THE_RED("theRed", "标红"),
    THE_YELLOW("theYellow", "标黄"),
    THE_BLUE("theBlue", "标蓝");

    private String colorType;
    private String colorDescribe;

    ColorStatusEnum(String colorType, String colorDescribe) {
        this.colorType = colorType;
        this.colorDescribe = colorDescribe;
    }

    public String colorType(){
        return this.colorType;
    }

    public String colorDescribe(){
        return this.colorDescribe;
    }

}
