package org.example;

public class SynchronizationDemo {
    private static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++)
                increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++)
                increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }

    private static synchronized void increment() {
        counter++;
    }
}
