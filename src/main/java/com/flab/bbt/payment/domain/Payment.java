package com.flab.bbt.payment.domain;

import lombok.Builder;

public class Payment {
    @Builder
    public Payment(long user_id, long order_id, int method, int status) {
        this.user_id = user_id;
        this.order_id = order_id;
        this.method = method;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
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
    private long user_id;
    private long order_id;
    private int method;
    private int status;
}
