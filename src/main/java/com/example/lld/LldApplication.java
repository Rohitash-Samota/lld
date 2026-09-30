package com.example.lld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.lld.ratelimiter.config.RateLimitProperties;

@SpringBootApplication
@EnableConfigurationProperties(RateLimitProperties.class)
public class LldApplication {

    public static void main(String[] args) {
        // Run Spring Boot
        SpringApplication.run(LldApplication.class, args);

        // Uncomment to run the Parking Lot Demo
        // ParkingLotDemo.main(new String[]{});
    }
}
