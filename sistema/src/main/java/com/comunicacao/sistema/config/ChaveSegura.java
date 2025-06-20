package com.comunicacao.sistema.config;

import java.security.SecureRandom;
import java.util.Base64;

public class ChaveSegura {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[64]; // 512 bits = 64 bytes
        secureRandom.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        System.out.println("Chave secreta segura: " + encodedKey);
    }
}
