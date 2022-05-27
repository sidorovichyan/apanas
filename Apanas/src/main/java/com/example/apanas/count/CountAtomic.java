package com.example.apanas.count;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CountAtomic {

    private static final AtomicInteger count = new AtomicInteger(0);            // счетчик подключений

    public void  increment() {
        count.getAndIncrement();
    }

    public static int value() {
        return count.get();
    }
}