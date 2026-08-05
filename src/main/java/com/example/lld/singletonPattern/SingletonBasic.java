package com.example.lld.singletonPattern;

final public class SingletonBasic {
    // Static variable to hold the single instance of the class
    public static SingletonBasic instance;

    // against instantiation of the class from outside, we make the constructor private
    private SingletonBasic() {
    }

    // synchronized thread-safe implementation and static method to get the instance of the singleton class
    public static synchronized SingletonBasic getInstance() {
        if (instance == null) {
            instance = new SingletonBasic();
        }
        return instance;
    }
    // Synchronized block implementation to get the instance of the singleton class
    @SuppressWarnings("DoubleCheckedLocking")
    public static SingletonBasic getInstanceWithSynchronizedBlock() {
        if (instance == null) {
            synchronized (SingletonBasic.class) {
                if (instance == null) {
                    instance = new SingletonBasic();
                }
            }
        }
        return instance;
    }
}
