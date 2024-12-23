package org.example.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingLocksAndConditions {

    public static void main(String[] args) {
        Worker worker = new Worker();
        new Thread(() -> worker.produce()).start();
        new Thread(() -> worker.consume()).start();
    }
}

class Worker {
    private int counter = 1;
    private final int MAX_SIZE = 5;
    private List<Integer> buffer = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    public void produce() {
        lock.lock();
        try{
            while(true){
                if(buffer.size() == MAX_SIZE){
                    System.out.println("Buffer full. Waiting for items to be consumed");
                    bufferNotFull.await();
                }
                System.out.println("Produced item >> " + counter);
                buffer.add(counter++);
                bufferNotEmpty.signal();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try{
            while(true){
                if(buffer.isEmpty()){
                    System.out.println("Buffer empty. Waiting for items to be produced");
                    bufferNotEmpty.await();
                }
                System.out.println("Consumed item << " + buffer.remove(buffer.size() - 1));
                counter--;
                bufferNotFull.signal();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}