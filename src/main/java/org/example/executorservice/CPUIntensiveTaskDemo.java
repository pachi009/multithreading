package org.example.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CPUIntensiveTaskDemo {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Cores: " +cores);
        ExecutorService service = Executors.newFixedThreadPool(cores);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            service.execute(() -> System.out.println("Task " +finalI +" is executed by " +Thread.currentThread().getName()));
        }
        service.shutdown();
    }
}