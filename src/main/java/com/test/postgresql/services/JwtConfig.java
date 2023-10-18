package com.test.postgresql.services;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    SecretKey secretKey() {
        String secretKeyString = "eGiLi6gerYO2bUGgXI49ZXx4XgWTL4fT3xY2yYW6dly4aGfEqNW7AbjPwZFTlhlFPY1S0UeNmRiKKbluvLZ0oMUAD6x0jtRWmVRz";
        byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretKeyBytes, "HmacSHA256");
    }
}
