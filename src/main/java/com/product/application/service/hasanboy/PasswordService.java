package com.product.application.service.hasanboy;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordService {
    public static String generateMD5(String password){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digist = md.digest();
            return DatatypeConverter.printHexBinary(digist).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
