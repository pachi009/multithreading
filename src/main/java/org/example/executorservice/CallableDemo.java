package org.example.executorservice;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> result = executor.submit(new CallableTask());
        System.out.println(result.get(1, TimeUnit.SECONDS));
        System.out.println("Main thread completed execution");
        result.cancel(true);
        executor.shutdown();
    }
}

class CallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return 555;
    }
}
