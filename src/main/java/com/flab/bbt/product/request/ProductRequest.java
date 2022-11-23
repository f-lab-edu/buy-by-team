package com.flab.bbt.product.request;

import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "시리얼 넘버는 필수 입력 값입니다.")
    private String serialNum;
    @NotBlank(message = "썸네일 이미지 url는 필수 입력 값입니다.")
    private String imgUrl;
    @NotNull(message = "정상가는 필수 입력 값입니다.")
    private int priceSale;
    @NotNull(message = "할인가는 필수 입력 값입니다.")
    private int priceDiscount;
    @NotNull(message = "할인율은 필수 입력 값입니다.")
    private int discountRate;

    @Builder
    public ProductRequest(String name, String serialNum, String imgUrl, int priceSale, int priceDiscount, int discountRate) {
        this.name = name;
        this.serialNum = serialNum;
        this.imgUrl = imgUrl;
        this.priceSale = priceSale;
        this.priceDiscount = priceDiscount;
        this.discountRate = discountRate;
    }

    public Product convertToEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .serialNum(request.getSerialNum())
                .imgUrl(request.getImgUrl())
                .priceSale(request.getPriceSale())
                .priceDiscount(request.getPriceDiscount())
                .discountRate(request.getDiscountRate())
                .build();
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
}
