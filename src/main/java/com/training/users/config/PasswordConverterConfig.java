package com.training.users.config;

import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

@Component
public class PasswordConverterConfig {


   public String convertToDatabaseColumn(String password) {
        VaultOperations vaultOperations = BeanUtil.getBean(VaultOperations.class);
        Plaintext plaintext = Plaintext.of(password);
        return vaultOperations.opsForTransit().encrypt("users", plaintext)
                .getCiphertext();
    }


    public String convertToEntityAttribute(String password) {
        VaultOperations vaultOperations = BeanUtil.getBean(VaultOperations.class);
        Ciphertext ciphertext = Ciphertext.of(password);
        return vaultOperations.opsForTransit().decrypt("users",
                ciphertext).asString();
    }

}
