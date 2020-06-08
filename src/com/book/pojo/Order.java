package com.book.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author zmj
 * @date 2020/5/4 18:20
 * @Description
 */
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal totalMoney;
    //订单状态:0未发货，1已发货，2已签收
    private Integer status;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal totalMoney, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.totalMoney = totalMoney;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", totalMoney=" + totalMoney +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
