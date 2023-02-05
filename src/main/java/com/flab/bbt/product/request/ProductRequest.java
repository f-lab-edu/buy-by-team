package com.flab.bbt.product.request;

import com.flab.bbt.product.domain.Product;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRequest {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "sku code는 필수 입력 값입니다.")
    private String skuCode;
    @NotBlank(message = "썸네일 이미지 url는 필수 입력 값입니다.")
    private String imgUrl;
    @NotNull(message = "정상가는 필수 입력 값입니다.")
    private int priceSale;
    @NotNull(message = "할인가는 필수 입력 값입니다.")
    private int priceDiscount;
    @NotNull(message = "할인율은 필수 입력 값입니다.")
    private int discountRate;


    public Product convertToEntity(ProductRequest request) {
        return Product.builder()
            .name(request.getName())
            .skuCode(request.getSkuCode())
            .imgUrl(request.getImgUrl())
            .build();
    }
}
