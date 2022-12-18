package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{
    private static Map<Long, Payment> paymentDb = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);
    @Override
    public void save(Payment payment) {
        payment.setId(sequence.incrementAndGet());
        paymentDb.put(payment.getId(), payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return Optional.ofNullable(paymentDb.get(id));
    }

    @Override
    public Payment updatePaymentStatusById(PaymentStatus status, Long id) {
        Payment payment = paymentDb.get(id).updateStatus(status);
        paymentDb.replace(id, payment);
        return payment;
    }
}
