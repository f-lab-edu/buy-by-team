package com.flab.bbt.product.response;

public class ProductResponse {
    private long id;
    private String name;
    private String serialNum;
    private String imgUrl;
    private int priceSale;
    private int priceDiscount;
    private int discountRate;

    public static ProductResponseBuilder builder(){
        return new ProductResponseBuilder();
    }
    public static class ProductResponseBuilder{
        private long id;
        private String name;
        private String serialNum;
        private String imgUrl;
        private int priceSale;
        private int priceDiscount;
        private int discountRate;

        ProductResponseBuilder(){}

        public ProductResponseBuilder name(String name){
            this.name = name;
            return this;
        }
        public ProductResponseBuilder serialNum(String serialNum){
            this.serialNum = serialNum;
            return this;
        }
        public ProductResponseBuilder imgUrl(String imgUrl){
            this.imgUrl = imgUrl;
            return this;
        }
        public ProductResponseBuilder priceSale(int priceSale){
            this.priceSale = priceSale;
            return this;
        }

        public ProductResponseBuilder priceDiscount(int priceDiscount){
            this.priceDiscount = priceDiscount;
            return this;
        }

        public ProductResponseBuilder discountRate(int discountRate){
            this.discountRate = discountRate;
            return this;
        }

        public ProductResponse build(){
            return new ProductResponse(this.name,this.serialNum,this.imgUrl,this.priceSale,this.priceDiscount,this.discountRate);
        }
    }

    public ProductResponse(String name, String serialNum, String imgUrl, int priceSale, int priceDiscount, int discountRate) {
        this.name = name;
        this.serialNum = serialNum;
        this.imgUrl = imgUrl;
        this.priceSale = priceSale;
        this.priceDiscount = priceDiscount;
        this.discountRate = discountRate;
    }
}
