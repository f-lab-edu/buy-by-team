package com.flab.bbt.payment.repository.mybatis;

import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import com.flab.bbt.payment.repository.PaymentMapper;
import com.flab.bbt.payment.repository.PaymentRepository;
import com.flab.bbt.product.repository.mybatis.ProductMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisPaymentRepositoryImpl implements PaymentRepository {

    private final PaymentMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {
        return paymentMapper.save(payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentMapper.findById(id);
    }

    @Override
    public Payment update(PaymentStatus status, Payment payment) {
        return paymentMapper.update(status.getStatusCode(), payment);
    }
}