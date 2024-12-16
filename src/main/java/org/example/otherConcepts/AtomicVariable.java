package org.example.otherConcepts;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {

        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        });

        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Counter: " +counter.get());
    }

}
