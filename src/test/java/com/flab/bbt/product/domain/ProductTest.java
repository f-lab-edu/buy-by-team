package com.flab.bbt.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void builder() {
        Product product = Product.builder()
            .name("노르웨이 생연어")
            .build();

        assertThat(product.getName()).isEqualTo("노르웨이 생연어");
    }
}