package com.flab.bbt.deal.repository.mybatis;

import com.flab.bbt.deal.domain.Deal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DealMapper {
    int save(Deal deal);
}
