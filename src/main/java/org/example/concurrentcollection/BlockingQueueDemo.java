package org.example.concurrentcollection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    private static final int CAPACITY = 8;
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(CAPACITY);

    public static void main(String[] args) {
//        Producer thread to produce data
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put(i);
                    System.out.println("Task produced: " +i);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumerOne = new Thread(() -> {
            try {
                while(true) {
                    int task = queue.take();
                    System.out.println("Task consumed: " +task +" by thread " +Thread.currentThread().getName());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "consumerOne");

        Thread consumerTwo = new Thread(() -> {
            try {
                while(true) {
                    int taskTaken = queue.take();
                    System.out.println("Task consumed: " +taskTaken +" by thread " +Thread.currentThread().getName());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "consumerTwo");

        producer.start();
        consumerOne.start();
        consumerTwo.start();
    }
}
