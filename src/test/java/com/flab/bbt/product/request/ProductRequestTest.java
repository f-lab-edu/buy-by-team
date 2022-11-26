package com.flab.bbt.product.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRequestTest {

    @Test
    void builder() {
        ProductRequest request = ProductRequest.builder().name("레몬라임과자").build();
        assertThat(request.getName()).isEqualTo("레몬라임과자");
    }
}