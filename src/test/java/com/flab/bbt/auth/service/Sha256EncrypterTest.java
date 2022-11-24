package com.flab.bbt.auth.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Sha256EncrypterTest {

    Sha256Encrypter encrypter = new Sha256Encrypter();

    @Test
    void encrypt() {
        String password = "temporaryPassword";

        String encryptedPassword = encrypter.encrypt(password);

        assertThat(encryptedPassword.length()).isEqualTo(64);
    }
}