package com.flab.bbt.deal.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 특정 Product로 생성되는 Deal의 정원/유효기간에 대한 정보 하나의 Product는 여러 개의 DealInfo를 가질 수 있다. 단, 특정 시점에 하나의
 * DealInfo만 active하다. (고민) isActive로 식별할지, 또는 'startTime', 'endTime' 칼럼을 생성해서 현재 시각이 'startTime'과
 * 'endTime' 사이에 있을 때 active하다고 판단할지
 * <p>
 * 전자로 할 경우 isActive인 DealInfo를 찾으면 돼서 간편함 후자는 DealInfo의 변천사(!)를 트랙킹하기에 더 좋다 (특정 시기에 Deal에 대한 정보를
 * 알아야 한다면) 후자로 하게 될 경우 유효한 DealInfo를 찾으려면 DealInfo.where(productId: pid).where("startTime <
 * ?",Time.now).where("endTime > ?", Time.now) startTime과 endTime으로 인덱스를 걸어야 할텐데 이게 과연 맞는가..?
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class DealInfo {

    private Long id;
    private Long productId;
    private int capacity; // Deal의 정원
    private int activeHours; // Deal이 유효한 시간
    private boolean isPrivate;
    //    private boolean isActive;
    private LocalDateTime startDate; // DealInfo가 유효한 기간
    private LocalDateTime endDate; // DealInfo가 유효한 기간
//    private List<Deal> dealList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
