package com.example.lld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LldApplication {

    public static void main(String[] args) {
        // Run Spring Boot
        SpringApplication.run(LldApplication.class, args);

        // Uncomment to run the Parking Lot Demo
        // ParkingLotDemo.main(new String[]{});
    }
}
