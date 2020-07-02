package com.gw.tool.wyc;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountData implements Serializable {

    /* 机构名称*/
    private String orgName;

    /** 下单时间 */
    private String orderTime;

    /** 支付时间 */
    private String payTime;

    /** 订单号 */
    private String orderNo;

    /** 部门名称 */
    private String departName;

    /** 用户名称 */
    private String userName;

    /** 用车类型 */
    private String type;

    /** 用车事由 */
    private String reason;

    /** 出发地 */
    private String startAddress;

    /** 目的地 */
    private String endAddress;

    /** 服务商 */
    private String serverName;

    /** 里程 */
    private BigDecimal licheng;

    /** 时长 */
    private long shichang;

    /** 原订单金额 */
    private BigDecimal orderAmount;

    /** 折后金额 */
    private BigDecimal selfAmount;

    /** 服务费 */
    private BigDecimal severAmount;

    /**  结算金额 */
    private BigDecimal actAmount;

    /**  结算状态 */
    private String actState;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public BigDecimal getLicheng() {
        return licheng;
    }

    public void setLicheng(BigDecimal licheng) {
        this.licheng = licheng;
    }

    public long getShichang() {
        return shichang;
    }

    public void setShichang(long shichang) {
        this.shichang = shichang;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getSelfAmount() {
        return selfAmount;
    }

    public void setSelfAmount(BigDecimal selfAmount) {
        this.selfAmount = selfAmount;
    }

    public BigDecimal getSeverAmount() {
        return severAmount;
    }

    public void setSeverAmount(BigDecimal severAmount) {
        this.severAmount = severAmount;
    }

    public BigDecimal getActAmount() {
        return actAmount;
    }

    public void setActAmount(BigDecimal actAmount) {
        this.actAmount = actAmount;
    }

    public String getActState() {
        return actState;
    }

    public void setActState(String actState) {
        this.actState = actState;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "orderTime='" + orderTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", departName='" + departName + '\'' +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", serverName='" + serverName + '\'' +
                ", licheng=" + licheng +
                ", shichang=" + shichang +
                ", orderAmount=" + orderAmount +
                ", selfAmount=" + selfAmount +
                ", severAmount=" + severAmount +
                ", actAmount=" + actAmount +
                ", actState='" + actState + '\'' +
                '}';
    }
}
