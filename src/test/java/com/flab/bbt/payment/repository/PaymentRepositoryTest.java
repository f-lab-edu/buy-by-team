package com.flab.bbt.payment.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class PaymentRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private PaymentRepository paymentRepository;

    Payment savedPayment;

    @BeforeEach
    public void setUp() {
        savedPayment = paymentRepository.save(buildPayment());
    }

    @Test
    @DisplayName("저장소에 성공적으로 저장된다.")
    void saveSuccessTest() {
        // given
        Payment payment = buildPayment();

        // then
        assertThat(savedPayment.getId()).isNotNull();
        assertThat(payment.getUserId()).isEqualTo(savedPayment.getUserId());
        assertThat(payment.getOrderId()).isEqualTo(savedPayment.getOrderId());
        assertThat(payment.getMethod()).isEqualTo(savedPayment.getMethod());
    }

    @Test
    @DisplayName("payment id로 성공적으로 조회된다.")
    void findByIdSuccessTest() {
        // when
        Optional<Payment> foundPayment = paymentRepository.findById(savedPayment.getId());

        // then
        assertThat(foundPayment.get().getId()).isEqualTo(savedPayment.getId());
    }

    @Test
    @DisplayName("성공적으로 payment의 상태를 완료로 업데이트 한다.")
    void statusUpdateSuccessTest() {
        // when
        paymentRepository.updatePaymentStatusById(PaymentStatus.SUCCESS, savedPayment.getId());
        Optional<Payment> updatedPayment = paymentRepository.findById(savedPayment.getId());

        // then
        assertThat(updatedPayment.get().getStatus()).isEqualTo(PaymentStatus.SUCCESS.getStatusCode());
    }

    private Payment buildPayment() {
        return Payment.builder()
            .userId(1L)
            .orderId(1L)
            .method(1)
            .status(PaymentStatus.NEW.getStatusCode())
            .build();
    }
}