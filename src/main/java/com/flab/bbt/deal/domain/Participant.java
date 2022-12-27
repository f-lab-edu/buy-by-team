package com.flab.bbt.deal.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Participant {
    private Long id;
    private Long dealId;
    private Long userId;
    private boolean isOwner;
}
