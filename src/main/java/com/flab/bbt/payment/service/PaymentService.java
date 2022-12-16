package com.flab.bbt.payment.service;

import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import com.flab.bbt.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void createPayment(Payment payment){
        // validation for payment

        // create payment

        // handle failure --> 여기서 안 할수도
    }

    public void completePayment(Payment payment){
        // complete payment
        paymentRepository.update(PaymentStatus.SUCCESS, payment);
    }


}
