package com.flab.bbt.order.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void builder() {
        Order order = Order.builder().dealId(1L).build();

        assertThat(order.getDealId()).isEqualTo(1L);
    }
}