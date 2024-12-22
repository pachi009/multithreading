package org.example.executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new ScheduledTask(), 1500, 2000, TimeUnit.MILLISECONDS);
        if(!executor.awaitTermination(10000, TimeUnit.MILLISECONDS)){
            System.out.println("Executor did not terminate after 10 secs. Hence graceful shutdown");
            executor.shutdownNow();
        }
    }
}

class ScheduledTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Polling endpoint for updates...");
    }
}
