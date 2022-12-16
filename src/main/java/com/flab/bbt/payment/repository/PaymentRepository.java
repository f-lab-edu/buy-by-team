package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;

import com.flab.bbt.payment.domain.PaymentStatus;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    Payment update(PaymentStatus status, Payment payment);
}
