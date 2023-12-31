package com.training.users.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("external-api")
@Data
public class VaultConfig {
    private String apiKey;

}

