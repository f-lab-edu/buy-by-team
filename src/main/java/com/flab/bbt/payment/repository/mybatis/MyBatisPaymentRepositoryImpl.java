package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisPaymentRepositoryImpl implements PaymentRepository{


    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return Optional.empty();
    }
}
