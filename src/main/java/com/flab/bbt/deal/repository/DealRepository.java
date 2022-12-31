package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import com.flab.bbt.product.domain.Product;
import java.time.LocalDateTime;
import java.util.Optional;

public interface DealRepository {
    Deal save(Deal deal);

    Optional<Deal> findById(Long id);
    Deal updateParticipantCountById(int updatedCount, Long id);

    Deal updateExpiredAtById(LocalDateTime time, Long id);
}
