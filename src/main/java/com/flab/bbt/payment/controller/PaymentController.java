package com.flab.bbt.payment.controller;

import com.flab.bbt.aop.LoginCheck;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.request.PaymentRequest;
import com.flab.bbt.payment.service.PaymentService;
import com.flab.bbt.user.domain.User;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("{paymentId}/complete")
    public CommonResponse completePayment(@PathVariable Long paymentId) {

        paymentService.completePayment(paymentId);
        return CommonResponse.success();
    }

    @LoginCheck
    @PostMapping("create")
    public CommonResponse createPayment(@Valid @RequestBody PaymentRequest paymentRequest,
        HttpSession session) {
        Payment payment = paymentService.createPayment(paymentRequest.convertToEntity());
        return CommonResponse.success(payment);
    }
}
