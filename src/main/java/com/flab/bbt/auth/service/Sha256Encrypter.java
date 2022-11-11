package com.flab.bbt.auth.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Sha256Encrypter implements PasswordEncrypter{
    public static final String ENCRYPTION_ALGORITHM = "SHA-256";

    @Override
    public String encrypt(String str) {
        StringBuffer sb = new StringBuffer();

        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            md.update(str.getBytes());
            byte[] data = md.digest();

            for (byte b : data) {
                sb.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
