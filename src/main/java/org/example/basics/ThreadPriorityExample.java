package org.example.basics;

public class ThreadPriorityExample {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() +" thread started");
        System.out.println(Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println(Thread.currentThread().getPriority());

        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 10; i++)
               System.out.println("Thread 1: " +i);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                System.out.println("Thread 2: " +i);
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                System.out.println("Thread 3: " +i);
        });

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t2.start();
        t1.start();
        t3.start();

        System.out.println(Thread.currentThread().getName() +" thread ended");
    }
}
