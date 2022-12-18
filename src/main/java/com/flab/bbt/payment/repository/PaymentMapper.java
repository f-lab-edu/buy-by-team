package com.flab.bbt.payment.repository;

import com.flab.bbt.payment.domain.Payment;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    Payment save(Payment payment);

    Optional<Payment> findById(Long id);

    Payment updateStatusById(@Param("status")Integer status, Long id);
}
