package com.example.lld.dateTimeApi;

// Issue with l

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args) {
        System.out.println("LocalDate");
        LocalDate nowDate = LocalDate.now();
        LocalDate myDate = LocalDate.of(2026, 7, 20);
        System.out.println(nowDate);
        System.out.println(myDate);

        System.out.println("LocalTime");
        LocalTime nowTime = LocalTime.now();
        LocalTime myTime = LocalTime.of(12, 55);
        System.out.println(nowTime);
        System.out.println(myTime);

        System.out.println("LocalDataTime");
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime myDateTime = LocalDateTime.of(myDate, myTime);
        System.out.println(nowDateTime);
        System.out.println(myDateTime);

        System.out.println("ZonedDateTime");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
