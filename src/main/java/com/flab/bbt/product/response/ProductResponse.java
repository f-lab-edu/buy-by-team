package com.flab.bbt.product.response;

import com.flab.bbt.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
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

    public static ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
            .name(product.getName())
            .imgUrl(product.getImgUrl())
            .priceSale(product.getPriceSale())
            .priceDiscount(product.getPriceDiscount())
            .discountRate(product.getDiscountRate())
            .build();
    }
}
