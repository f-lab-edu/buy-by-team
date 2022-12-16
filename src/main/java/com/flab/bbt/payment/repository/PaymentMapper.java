package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    Payment save(Payment payment);

    Optional<Payment> findById(Long id);

    Payment update(@Param("status")Integer status, @Param("param")Payment payment);
}
