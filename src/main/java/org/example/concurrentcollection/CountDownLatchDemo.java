package org.example.concurrentcollection;

// Let's assume there are 3 chefs in a restaurant to prepare 3 dishes.
// The restaurant can start serving customers once all dishes are ready.
// We can use CountDownLatch to handle this.
// We can use join method calls on each thread instead of CountDownLatch.
// But, in situation where dynamic no of threads are created, CountDownLatch shines.

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int noOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(noOfChefs);
        Thread t1 = new Thread(new Chef("Chef-1", "Idli", latch));
        Thread t2 = new Thread(new Chef("Chef-2", "Sambar", latch));
        Thread t3 = new Thread(new Chef("Chef-3", "Chutney", latch));
        t1.start();
        t2.start();
        t3.start();

//        t1.join();
//        t2.join();
//        t3.join();

        latch.await();
        System.out.println("All dishes are ready. Let's start serving customers");
    }
}

class Chef implements Runnable{

    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(name +" is preparing " +dish);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name +" completed preparing " + dish);
        latch.countDown();
    }
}
