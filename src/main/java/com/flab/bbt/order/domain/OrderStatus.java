package com.flab.bbt.order.domain;

public enum OrderStatus {

    CREATED,                // 오더 생성 상태
    PAID,                   // 결제 완료 및 딜 성사 대기 상태
    COMPLETED,              // 해당 오더의 딜이 성사되어 오더가 최종 완료된 상태
    CANCLED                 // 오더가 취소된 상태
}
