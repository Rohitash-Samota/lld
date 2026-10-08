package com.example.lld.designPatterns.creational;

public class Singleton {
    // Static member to hold the single instance
    private static Singleton obj;

    // private constructor to force use of getInstance() to create Singleton object
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (obj == null)
            obj = new Singleton();
        return obj;
    }
}
