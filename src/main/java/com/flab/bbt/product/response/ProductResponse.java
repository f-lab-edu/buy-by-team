package com.flab.bbt.product.response;

import com.flab.bbt.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ProductResponse {

    private long id;
    private String name;
    private String imgUrl;

    public static ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .imgUrl(product.getImgUrl())
            .build();
    }
}
