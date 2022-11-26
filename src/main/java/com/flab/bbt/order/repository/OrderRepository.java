package com.flab.bbt.order.repository;

import com.flab.bbt.order.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
}
