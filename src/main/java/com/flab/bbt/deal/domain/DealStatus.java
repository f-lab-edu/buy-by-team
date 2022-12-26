package com.flab.bbt.deal.domain;

public enum DealStatus {
    CREATED,        // 생성됨, 참여자0, 참여 불가
    IN_PROGRESS,    // 진행중, 참여자 1명이상, 참여 가능
    FAIL,           // 기한 만료
    COMPLETED       // 기한 내 목표인원 모집 성공
}
