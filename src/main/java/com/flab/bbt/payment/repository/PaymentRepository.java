package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;

import com.flab.bbt.payment.domain.PaymentStatus;
import java.util.Optional;

public interface PaymentRepository {
    void save(Payment payment);
    Optional<Payment> findById(Long id);
    Payment updatePaymentStatusById(PaymentStatus status, Long id);
}
