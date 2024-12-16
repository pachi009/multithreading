package org.example.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {
    private int counter = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment(){
        lock.writeLock().lock();
        try{
            counter++;
            System.out.println(Thread.currentThread().getName() +" writes: " +counter);
        } finally{
            lock.writeLock().unlock();
        }
    }

    public void getValue(){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() +" reads: " +counter);
        } finally{
            lock.readLock().unlock();
        }
    }
}

class ReadWriteLockDemo{
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        for(int i=0; i< 2; i++){
            Thread readThread = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    sharedResource.getValue();
                }
            });
            readThread.setName("Reader Thread " +(i+1));
            readThread.start();
        }

        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedResource.increment();
            }
        });
        writerThread.setName("Writer Thread");
        writerThread.start();
    }
}