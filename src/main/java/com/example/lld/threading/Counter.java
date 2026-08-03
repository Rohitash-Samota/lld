package com.example.lld.threading;

public class Counter {
    private int counter = 0;

    // Block Level
    // public void increment() {
    // synchronized (this) {
    // counter++;
    // }
    // }
    // optimize version

    // Function level
    public synchronized void increment() {
        counter++;
    }

    public int getCount() {
        return counter;
    }
}
