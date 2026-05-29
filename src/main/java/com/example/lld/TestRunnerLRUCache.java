package com.example.lld;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.lld.services.LRUCacheService;

@Component
public class TestRunnerLRUCache implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        LRUCacheService<Integer, String> cache = new LRUCacheService<>();

        System.out.println("STEP 1 : PUT 1");
        cache.put(1, "A");
        cache.printCache();

        System.out.println("------------------------");

        System.out.println("STEP 2 : PUT 2");
        cache.put(2, "B");
        cache.printCache();

        System.out.println("------------------------");

        System.out.println("STEP 3 : PUT 3");
        cache.put(3, "C");
        cache.printCache();

        System.out.println("------------------------");

        System.out.println("STEP 4 : PUT 4");
        cache.put(4, "D");
        cache.printCache();

        System.out.println("------------------------");

        System.out.println("STEP 5 : GET 2");
        System.out.println("VALUE : " + cache.get(2));
        cache.printCache();

        System.out.println("------------------------");

        System.out.println("STEP 6 : PUT 5");
        cache.put(5, "E");
        cache.printCache();

        System.out.println("------------------------");
    }
}
