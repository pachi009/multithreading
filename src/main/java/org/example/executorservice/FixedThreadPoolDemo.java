package org.example.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 15; i++) {
            executor.execute(new Work(i));
        }

//      Simply create a runnable instance and call the execute method
        for (int i = 0; i < 15; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println("Task with ID: " + finalI + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
    }
}

class Work implements Runnable {
    private final int taskId;
    public Work(int taskId) {
        this.taskId = taskId;
    }
    public void run() {
        System.out.println("Task with ID: " + taskId + " executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}