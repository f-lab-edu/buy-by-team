package com.flab.bbt.payment.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import com.flab.bbt.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        // validation for payment --> 로직 추가 예정?

        // create payment
        paymentRepository.save(payment);
        return payment;
    }

    public Payment completePayment(Long paymentId){

        Payment payment = findPaymentById(paymentId);
        Payment updatedPayment = paymentRepository.updatePaymentStatusById(PaymentStatus.SUCCESS, payment.getId());
        return updatedPayment;
    }

    public Payment findPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
            .orElseThrow(() -> new CustomException(ErrorCode.PAYMENT_NOT_FOUND));
    }


}
