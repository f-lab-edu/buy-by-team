package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(Long id);
}
