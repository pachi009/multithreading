package org.example.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private int counter = 0;

    public void increment(){
        try{
            lock.writeLock().lock();
            counter++;
            System.out.println(Thread.currentThread().getName() +" writes >> " + counter);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void getValue(){
        try{
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() +" reads << " +counter);
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo{
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        for (int i = 0; i < 2; i++) {
            Thread reader = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    sharedResource.getValue();
                }
            });
            reader.setName("Reader Thread " +(i+1));
            reader.start();
        }
        for (int i = 0; i < 2; i++) {
            Thread writer = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    sharedResource.increment();
                }
            });
            writer.setName("Writer Thread " +(i+1));
            writer.start();
        }
    }
}