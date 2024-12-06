package org.example.concurrentcollection;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MultiStageTour {

    private static final int TOURISTS = 5;
    private static final int STAGES = 3;
    public static final CyclicBarrier barrier = new CyclicBarrier(TOURISTS, () ->
        System.out.println("The Tourist Guide starts speaking ........")
    );

    public static void main(String[] args) {
        for (int i = 0; i < TOURISTS; i++) {
            Thread t = new Thread(new Tourist());
            t.start();
        }
    }

    static class Tourist implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 1; i <= STAGES; i++) {
                    Thread.sleep(500);
                    System.out.println("The Tourist " +Thread.currentThread().getName() +" has reached the assembly stage: " +i);
                    barrier.await();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


