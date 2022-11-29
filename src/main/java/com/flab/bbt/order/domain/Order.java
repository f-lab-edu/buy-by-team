package com.flab.bbt.order.domain;

import lombok.Builder;

public class Order {
    private long id;
    private long userId;
    private long productId;
    private long paymentId;
    private int quantity;


    @Builder
    public Order(long userId, long productId, long paymentId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.paymentId = paymentId;
        this.quantity = quantity;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }





}
