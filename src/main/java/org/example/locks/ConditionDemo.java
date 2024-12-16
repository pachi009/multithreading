package org.example.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private final Integer MAX_SIZE = 5;
    private final Queue<Integer> queue = new LinkedList<Integer>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public void produce(Integer item) throws InterruptedException {
        lock.lock();
        try{
            while(queue.size() == MAX_SIZE){
                notFull.await();
            }
            queue.add(item);
            notEmpty.signal();
            System.out.println("Produced >> " + item);
        } finally{
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try{
            while(queue.isEmpty()){
                notEmpty.await();
            }
            notFull.signal();
            System.out.println("Consumed << " + queue.poll());
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();

        Thread producer = new Thread(() -> {
           try{
               for(int i = 0; i < 10; i++){
                   demo.produce(i);
                   Thread.sleep(1000);
               }
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
        });

        Thread consumer = new Thread(() -> {
            try{
                for(int i = 0; i < 10; i++){
                    demo.consume();
                    Thread.sleep(1300);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
