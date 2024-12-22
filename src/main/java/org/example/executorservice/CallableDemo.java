package org.example.executorservice;

import java.util.concurrent.*;
// Callable is used when we want threads to return some value/result back to the invoker.
// It uses generics. Hence, type must be passed while implementing this interface
// Return type is Future<T>. The .get method must be invoked to get the actual result
// The main thread is blocked until actual result is available

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> result = executor.submit(new CallableTask());
        System.out.println(result.get());

//      Throws TimeoutException if result is not available within specified time duration
        System.out.println(result.get(1, TimeUnit.SECONDS));

        System.out.println("result.cancel(true): " + result.cancel(true));
        System.out.println("Is Cancelled: " +result.isCancelled());
        System.out.println("Is done: " +result.isDone());

        System.out.println("Main thread completed execution");
        executor.shutdown();
    }
}

class CallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 555;
    }
}
