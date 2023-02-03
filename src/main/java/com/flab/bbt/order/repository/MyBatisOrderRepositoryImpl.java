package com.flab.bbt.order.repository;

import com.flab.bbt.order.domain.Order;
import com.flab.bbt.order.domain.OrderStatus;
import com.flab.bbt.order.repository.mybatis.OrderMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepositoryImpl implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        order.setStatus(OrderStatus.CREATED);
        orderMapper.save(order);

        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }
}
