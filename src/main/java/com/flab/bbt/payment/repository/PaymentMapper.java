package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    Payment save(Payment payment);

    Optional<Payment> findById(Long id);
}
