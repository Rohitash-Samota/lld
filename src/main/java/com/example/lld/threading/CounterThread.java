package com.example.lld.threading;

public class CounterThread extends Thread {

    private final Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }

        System.out.println(getName() + " Finished");
    }
}