package com.cy.asset.customer.bean;

public enum RelationEnum {

    ONESELF("oneself", "本人"),
    PARENTS("parents", "父母"),
    SPOUSE("spouse", "配偶"),
    CHILDREN("children", "子女"),
    FAMILY("family", "家庭"),
    UNIT("unit", "单位"),
    BILL("bill", "账单"),
    OTHER("other", "其他");

    private String relationType;
    private String relationDescribe;

    RelationEnum(String relationType, String relationDescribe) {
        this.relationType = relationType;
        this.relationDescribe = relationDescribe;
    }

    public String relationType(){
        return this.relationType;
    }

    public String relationDescribe(){
        return this.relationDescribe;
    }

}
