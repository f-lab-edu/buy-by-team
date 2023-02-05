package com.flab.bbt.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
public class Product {

    @Setter
    private long id;
    private String name;
    private String skuCode;
    private String imgUrl;
}
