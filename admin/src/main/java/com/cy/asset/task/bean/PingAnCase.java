package com.cy.asset.task.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author wc
 * @date 2020/12/2
 * @describe 平安个案信息
 */
public class PingAnCase extends CaseBean{

    /** 主键 **/
    private String id;

    @Excel(name = "客户姓名", orderNum = "0")
    private String customerName;
    @Excel(name = "客户性别", orderNum = "1")
    private String customerSex;
    @Excel(name = "手别", orderNum = "2")
    private String handOther;
    @Excel(name = "委托批次", orderNum = "3")
    private String entrustBatch;
    @Excel(name = "委外大区", orderNum = "4")
    private String outsourcingDistrict;
    @Excel(name = "BLK", orderNum = "5")
    private String BLK;
    @Excel(name = "长委次数", orderNum = "6")
    private String longCommitteeNumber;
    @Excel(name = "Acode日期", orderNum = "7")
    private String acodeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getHandOther() {
        return handOther;
    }

    public void setHandOther(String handOther) {
        this.handOther = handOther;
    }

    public String getEntrustBatch() {
        return entrustBatch;
    }

    public void setEntrustBatch(String entrustBatch) {
        this.entrustBatch = entrustBatch;
    }

    public String getOutsourcingDistrict() {
        return outsourcingDistrict;
    }

    public void setOutsourcingDistrict(String outsourcingDistrict) {
        this.outsourcingDistrict = outsourcingDistrict;
    }

    public String getBLK() {
        return BLK;
    }

    public void setBLK(String BLK) {
        this.BLK = BLK;
    }

    public String getLongCommitteeNumber() {
        return longCommitteeNumber;
    }

    public void setLongCommitteeNumber(String longCommitteeNumber) {
        this.longCommitteeNumber = longCommitteeNumber;
    }

    public String getAcodeDate() {
        return acodeDate;
    }

    public void setAcodeDate(String acodeDate) {
        this.acodeDate = acodeDate;
    }

    @Override
    public String toString() {
        return "PingAnCase{" +
                "customerName='" + customerName + '\'' +
                ", customerSex='" + customerSex + '\'' +
                ", handOther='" + handOther + '\'' +
                ", entrustBatch='" + entrustBatch + '\'' +
                ", outsourcingDistrict='" + outsourcingDistrict + '\'' +
                ", BLK='" + BLK + '\'' +
                ", longCommitteeNumber='" + longCommitteeNumber + '\'' +
                ", acodeDate='" + acodeDate + '\'' +
                '}';
    }
}
