package com.flab.bbt.deal.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DealRequest {

    @NotNull(message = "제품 id는 필수 입력 값입니다.")
    private Long productId;
}
