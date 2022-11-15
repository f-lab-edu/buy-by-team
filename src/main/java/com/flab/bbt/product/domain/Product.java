package com.flab.bbt.product.domain;

import com.flab.bbt.user.domain.User;

public class Product {
    private long id;
    private String name;
    private String serialNum;
    private String imgUrl;
    private int priceSale;
    private int priceDiscount;
    private int discountRate;

    public Product(String name, String serialNum, String imgUrl, int priceSale, int priceDiscount, int discountRate) {
        this.name = name;
        this.serialNum = serialNum;
        this.imgUrl = imgUrl;
        this.priceSale = priceSale;
        this.priceDiscount = priceDiscount;
        this.discountRate = discountRate;
    }

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

    public static Product.ProductBuilder builder() {
        return new Product.ProductBuilder();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public static class ProductBuilder {
        private String name;
        private String serialNum;
        private String imgUrl;
        private int priceSale;
        private int priceDiscount;
        private int discountRate;

        ProductBuilder(){}

        public Product.ProductBuilder name(String name) {
            this.name = name;

            return this;
        }

        public Product.ProductBuilder serialNum(String serialNum) {
            this.serialNum = serialNum;

            return this;
        }

        public Product.ProductBuilder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;

            return this;
        }

        public Product.ProductBuilder priceSale(int priceSale) {
            this.priceSale = priceSale;

            return this;
        }

        public Product.ProductBuilder priceDiscount(int priceDiscount) {
            this.priceDiscount = priceDiscount;
            return this;
        }

        public Product.ProductBuilder discountRate(int discountRate) {
            this.discountRate = discountRate;
            return this;
        }

        public Product build() {
            return new Product(this.name, this.serialNum, this.imgUrl,this.priceSale,this.priceDiscount, this.discountRate);
        }

    }
}
