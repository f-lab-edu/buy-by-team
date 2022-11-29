package com.flab.bbt.purchase.service;

import com.flab.bbt.order.repository.OrderRepository;
import com.flab.bbt.payment.repository.PaymentRepository;
import com.flab.bbt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;


    public PurchaseService(UserRepository userRepository, OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    // 유저의 주문 결제를 위한 서비스
    // 프로젝트 세팅하며서 임시로 만들어 두는 것이라 마음껏 지우셔도 됩니다..
}
