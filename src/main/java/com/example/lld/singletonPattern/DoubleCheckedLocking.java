package com.example.lld.singletonPattern;

public class DoubleCheckedLocking {
    private static volatile DoubleCheckedLocking instance;

    // don't instantiate the class from outside, we make the constructor private
    private DoubleCheckedLocking() {
    }

    @SuppressWarnings("DoubleCheckedLocking")
    public static DoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }    
}
