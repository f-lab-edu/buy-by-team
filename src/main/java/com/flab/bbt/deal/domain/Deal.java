package com.flab.bbt.deal.domain;

import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Deal {
    private Long id;
    private User owner;
    private Product product;
    private int targetNum;
    private int participantNum;
    private DealStatus dealStatus;
    private boolean isPrivate;
    private String targetPeriod;
    private LocalDateTime expiredAt;
}
