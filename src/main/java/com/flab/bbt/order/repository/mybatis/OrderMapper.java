package com.flab.bbt.order.repository.mybatis;

import com.flab.bbt.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    int save(Order order);
}
