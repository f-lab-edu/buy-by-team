package com.flab.bbt.order.repository;

import com.flab.bbt.order.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private static Map<Long, Order> orderDb = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public Order save(Order order) {
        order.setId(sequence.incrementAndGet());
        orderDb.put(order.getProductId(), order);

        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderDb.get(id));
    }
}
