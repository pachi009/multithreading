package org.example.basics;

public class JoinThreadExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread Started");
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                System.out.println("Thread 1: " +i);
        });

        Thread t2 = new Thread(() -> {
           for (int i = 0; i < 10; i++)
               System.out.println("Thread 2: " +i);
        });

        t1.start();
        t2.start();

        t1.join();
//        t2.join();
        System.out.println("Main Thread Finished");
    }
}
