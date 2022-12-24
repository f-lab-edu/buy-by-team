package com.flab.bbt.payment.domain;

import lombok.Builder;

public class Payment {

    @Builder
    public Payment(long userId, long orderId, int method, int status) {
        this.userId = userId;
        this.orderId = orderId;
        this.method = method;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private long id;
    private long userId;
    private long orderId;
    private int method;
    private int status;
}
