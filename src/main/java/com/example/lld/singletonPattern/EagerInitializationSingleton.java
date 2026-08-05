package com.example.lld.singletonPattern;

// this Singleton instance is created at the time of class loading and create final so not changeable.
//  This is the easiest way to create a singleton class but it has a drawback that instance is created even though client application might not be using it.
public class EagerInitializationSingleton {
    private static final EagerInitializationSingleton instance = new EagerInitializationSingleton();

    private EagerInitializationSingleton() {
    }

    public static EagerInitializationSingleton getInstance() {
        return instance;
    }
}
