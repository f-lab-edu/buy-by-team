package com.flab.bbt.product.response;

import lombok.Builder;

public class ProductResponse {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public int getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(int priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    private long id;
    private String name;
    private String serialNum;
    private String imgUrl;
    private int priceSale;
    private int priceDiscount;
    private int discountRate;

    @Builder
    public ProductResponse(String name, String serialNum, String imgUrl, int priceSale,
        int priceDiscount, int discountRate) {
        this.name = name;
        this.serialNum = serialNum;
        this.imgUrl = imgUrl;
        this.priceSale = priceSale;
        this.priceDiscount = priceDiscount;
        this.discountRate = discountRate;
    }
}
