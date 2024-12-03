package org.example.concurrentcollection;

import java.util.concurrent.CountDownLatch;

public class Restaurant {
    public static void main(String[] args) throws InterruptedException {
        int numberOfChefs = 3;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfChefs);
        new Thread(new Chef("Chef-1", "Idli", countDownLatch)).start();
        new Thread(new Chef("Chef-2", "Sambar", countDownLatch)).start();
        new Thread(new Chef("Chef-3", "Dosa", countDownLatch)).start();

        countDownLatch.await();
        System.out.println("All dishes are ready. Let's serve the customers");
    }
}

class Chef implements Runnable {

    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    public Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    public void run() {
        try {
            System.out.println(name +" has started preparing " +dish);
            Thread.sleep(3000);
            System.out.println(name +" has finished preparing " +dish);
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}