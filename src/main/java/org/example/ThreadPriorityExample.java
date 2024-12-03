package org.example;

public class ThreadPriorityExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 10; i++)
               System.out.println("Thread 1: " +i);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                System.out.println("Thread 2: " +i);
        });

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t2.start();
        t1.start();

    }
}
