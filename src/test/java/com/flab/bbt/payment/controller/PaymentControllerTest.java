package com.flab.bbt.payment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import com.flab.bbt.payment.request.PaymentRequest;
import com.flab.bbt.payment.service.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class PaymentControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    @Test
    @DisplayName("payment가 생성되면 200 ok를 내려준다.")
    void createPaymentSuccessTest() throws Exception {
        // given
        PaymentRequest paymentRequest = PaymentRequest.builder()
            .userId(1L)
            .orderId(1L)
            .method(1)
            .build();

        String content = objectMapper.writeValueAsString(paymentRequest);

        // when
        mockMvc.perform(post("/payment/create")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("payment의 상태를 완료로 업데이트 후 200 ok를 내려준다.")
    void completePaymentSuccessTest() throws Exception {
        // given
        when(paymentService.findPaymentById(anyLong())).thenReturn(getPayment());

        // when
        mockMvc.perform(post("/payment/1/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    private Payment getPayment() {
        return Payment.builder()
            .id(1L)
            .userId(1L)
            .orderId(1L)
            .method(1)
            .status(PaymentStatus.NEW.getStatusCode())
            .build();
    }
}