package com.flab.bbt.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Product {

    private long id;
    private String name;
    private String skuCode;
    private String imgUrl;
    private int priceSale;
    private int priceDiscount;
    private int discountRate;
}
