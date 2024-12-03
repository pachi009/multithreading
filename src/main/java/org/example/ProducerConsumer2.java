package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer2 {
    public static void main(String[] args) {
        Worker2 worker = new Worker2(5, 0);
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

class Worker2{
    private int sequence = 0;
    private final int top;
    private final int bottom;
    private List<Integer> container;
    private Object lock = new Object();

    Worker2(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        container = new ArrayList<Integer>();
    }


    public void produce() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(container.size() == top){
                    System.out.println("Container is full. Waiting for items to be consumed");
                    lock.wait();
                } else {
                    System.out.println(sequence +" added to the container");
                    container.add(sequence++);
                    lock.notify();
                }
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(container.size() == bottom){
                    System.out.println("Container is empty. Waiting for items to be produced");
                    lock.wait();
                } else {
                    System.out.println(container.remove(container.size()-1) +" removed from the container");
                    lock.notify();
                }
                Thread.sleep(1000);
            }
        }
    }
}
