package com.example.lld.threading;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        CounterThread t1 = new CounterThread(counter);
        CounterThread t2 = new CounterThread(counter);
        CounterThread t3 = new CounterThread(counter);
        CounterThread t4 = new CounterThread(counter);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Expected Count : 400000");
        System.out.println("Actual Count   : " + counter.getCount());
    }
}