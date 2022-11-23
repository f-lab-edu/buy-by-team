package com.flab.bbt.product.response;

import com.flab.bbt.product.domain.Product;

public class ProductResponse {
    private long id;
    private String name;
    private String imgUrl;
    private int priceSale;
    private int priceDiscount;
    private int discountRate;

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

    public static ProductResponse.ProductResponseBuilder builder(){
        return new ProductResponse.ProductResponseBuilder();
    }
    public static class ProductResponseBuilder{
        private long id;
        private String name;
        private String imgUrl;
        private int priceSale;
        private int priceDiscount;
        private int discountRate;

        ProductResponseBuilder(){}

        public ProductResponse.ProductResponseBuilder name(String name){
            this.name = name;
            return this;
        }
        public ProductResponse.ProductResponseBuilder imgUrl(String imgUrl){
            this.imgUrl = imgUrl;
            return this;
        }
        public ProductResponse.ProductResponseBuilder priceSale(int priceSale){
            this.priceSale = priceSale;
            return this;
        }

        public ProductResponse.ProductResponseBuilder priceDiscount(int priceDiscount){
            this.priceDiscount = priceDiscount;
            return this;
        }

        public ProductResponse.ProductResponseBuilder discountRate(int discountRate){
            this.discountRate = discountRate;
            return this;
        }

        public ProductResponse build(){
            return new ProductResponse(this.name,this.imgUrl,this.priceSale,this.priceDiscount,this.discountRate);
        }
    }

    public ProductResponse(String name, String imgUrl, int priceSale, int priceDiscount, int discountRate) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.priceSale = priceSale;
        this.priceDiscount = priceDiscount;
        this.discountRate = discountRate;
    }

    public static ProductResponse convertToProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .imgUrl(product.getImgUrl())
                .priceSale(product.getPriceSale())
                .priceDiscount(product.getPriceDiscount())
                .discountRate(product.getDiscountRate())
                .build();
    }
}
