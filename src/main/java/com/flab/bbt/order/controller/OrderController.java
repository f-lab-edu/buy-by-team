package com.flab.bbt.order.controller;

import com.flab.bbt.aop.LoginCheck;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.deal.service.DealService;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.order.domain.Order;
import com.flab.bbt.order.request.OrderRequest;
import com.flab.bbt.order.service.OrderService;
import com.flab.bbt.user.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final DealService dealService;

    @LoginCheck
    @PostMapping()
    public CommonResponse createOrder(@Valid @RequestBody OrderRequest orderRequest,
        HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.COOKIE_SESSION_ID);

        // 유저가 이미 해당 딜에 참여한 상태일 경우의 처리
        if (dealService.isParticipant(orderRequest.getDealId(), user.getId())) {
            throw new CustomException(ErrorCode.ALEADY_PARTICIPATE_IN_DEAL);
        }

        // TODO("딜 예약자 수가 이미 꽉 찼을 경우의 처리")

        Order order = orderService.createOrder(orderRequest.convertToEntity());

        return CommonResponse.success(order);
    }
}
