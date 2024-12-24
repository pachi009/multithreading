package org.example.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private int sharedData = 0;

    public void methodA(){
        lock.lock();
        try{
            sharedData++    ;
            System.out.println("Method A : " +sharedData);
            methodB();
        } finally{
            lock.unlock();
        }
    }

    private void methodB() {
        lock.lock();
        try{
            sharedData --;
            System.out.println("Method B : " +sharedData);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        for (int i = 0; i < 5; i++) {
//            Using lambda
//            new Thread(() -> demo.methodA()).start();
//            Using method reference
            new Thread(demo::methodA).start();
        }
    }
}
