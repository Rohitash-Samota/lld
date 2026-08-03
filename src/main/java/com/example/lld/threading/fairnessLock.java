package com.example.lld.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class fairnessLock {
    // if we pass ReentrantLock in true than thread change in given os schedular
    // order
    private final Lock lock = new ReentrantLock(true);

    public void outerMethod() {
        try {
            lock.lock();
            System.out.println("Outer Method");
            this.innerMethod();
        } catch (Exception e) {
            System.out.println("Outer Method Error => " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        try {
            lock.lock();
            System.out.println("Inner Method");
        } catch (Exception e) {
            System.err.println("Inner Method Error => " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
