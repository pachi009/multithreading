package org.example.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntensiveTask {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);
        System.out.println("Created executor with " +cores);
        for(int i=0; i<20; i++){
            executor.execute(new CpuTask());
        }
        executor.shutdown();
    }
}

class CpuTask implements Runnable {
    public void run(){
        System.out.println("CPU Intensive task executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
