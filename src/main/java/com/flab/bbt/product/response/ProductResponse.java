package com.flab.bbt.product.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductResponse {

    private long id;
    private String name;
    private String skuCode;
    private String imgUrl;
    private int priceSale;
    private int priceDiscount;
    private int discountRate;
}
