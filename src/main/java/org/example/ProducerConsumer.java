package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) {
        Worker worker = new Worker(5, 0);

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}

class Worker{
    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private List<Integer> container;
    private final Object lock = new Object();

    public Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<Integer>();
    }

    public void produce() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(container.size() == top){
                    System.out.println("Container is full. Waiting for items to be removed");
                    lock.wait();
                } else {
                    System.out.println("Sequence " +sequence +" added to the container");
                    container.add(sequence++);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(container.size() == bottom){
                    System.out.println("Container is empty. Waiting for items to be added");
                    lock.wait();
                } else{
                    System.out.println("Sequence " +container.remove(container.size()-1) +" removed from the container");
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
