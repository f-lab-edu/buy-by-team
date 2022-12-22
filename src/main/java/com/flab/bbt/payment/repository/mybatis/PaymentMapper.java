package com.flab.bbt.payment.repository.mybatis;

import com.flab.bbt.payment.domain.Payment;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    int save(@Param("payment") Payment payment);

    Optional<Payment> findById(Long id);

    Payment updateStatusById(@Param("status")Integer status, Long id);
}
