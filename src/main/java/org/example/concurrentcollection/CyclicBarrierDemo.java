package org.example.concurrentcollection;

// Tour Guide waits for all the tourists to assemble at a stage and then starts explaining.
// There could be any number of stages/places to visit.

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final int NUM_TOURISTS = 5;
    private static final int NUM_STAGES = 3;
    private static final CyclicBarrier barrier =
            new CyclicBarrier(NUM_TOURISTS, () -> System.out.println("Tour Guide starts speaking..."));

    public static void main(String[] args) {
        for (int i = 1; i <= NUM_TOURISTS; i++) {
            new Thread(new Tourist(i)).start();
        }
    }

    static class Tourist implements Runnable {

        int touristId;
        public Tourist(int touristId) {
            this.touristId = touristId;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 1; i <= NUM_STAGES; i++) {
                System.out.println("Tourist # " + touristId + " has arrived at stage " + i);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
