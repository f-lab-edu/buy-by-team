package com.flab.bbt.order.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.order.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class OrderRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("저장소에 성공적으로 저장된다.")
    void saveSuccessTest() {
        // given
        Order order = buildOrder();

        // when
        Order savedOrder = orderRepository.save(order);

        // then
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(order.getDealId()).isEqualTo(savedOrder.getDealId());
        assertThat(order.getUserId()).isEqualTo(savedOrder.getUserId());
        assertThat(order.getQuantity()).isEqualTo(savedOrder.getQuantity());
    }

    private Order buildOrder() {
        return Order.builder()
            .dealId(1L)
            .userId(1L)
            .quantity(1)
            .build();
    }

}