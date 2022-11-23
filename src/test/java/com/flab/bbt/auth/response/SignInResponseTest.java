package com.flab.bbt.auth.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SignInResponseTest {

    @Test
    void builder() {
        SignInResponse response = SignInResponse.builder().email("jungin@gmail.com").build();

        assertThat(response.getEmail()).isEqualTo("jungin@gmail.com");
    }
}