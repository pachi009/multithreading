package org.example.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available Processors: " + cores);
        for (int i = 0; i < 10; i++) {
            executor.execute(new CachedWork(i));
        }
        executor.shutdown();
    }
}

class CachedWork implements Runnable {
    private final int taskId;

    CachedWork(int taskId) {
        this.taskId = taskId;
    }

    public void run() {
        System.out.println("Task with id " + taskId +" executed by " +Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}