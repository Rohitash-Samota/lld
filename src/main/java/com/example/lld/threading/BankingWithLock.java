package com.example.lld.threading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankingWithLock {
    private final Lock lock = new ReentrantLock();

    private int myAmount = 0;

    public void addAmount(int amount) {
        try {
            if (lock.tryLock(10000, TimeUnit.MICROSECONDS)) {
                Thread.sleep(10000);
                this.myAmount += amount;
            }
        } catch (Exception e) {
            System.out.println("Exception => " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public int getAmount() {

        return myAmount;
    }
}
