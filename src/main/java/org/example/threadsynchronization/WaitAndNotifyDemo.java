package org.example.threadsynchronization;

public class WaitAndNotifyDemo {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                method1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            method2();
        });

        t1.start();
        t2.start();
    }
//  Thread.sleep --> pause the execution
//  lock.wait --> Inter thread communication and synchronization

    private static void method1() throws InterruptedException {
        synchronized(LOCK) {
            System.out.println("Hello from method 1");
            LOCK.wait();
            System.out.println("Back again in the method 1");
        }
    }

//    The thread calling notify does not release the lock immediately.
//    It releases the lock only after executing the synchronized
//    method or block. The notified thread must wait until the
//    current thread to finish its execution
    private static void method2() {
        synchronized (LOCK) {
            System.out.println("Hello from method 2");
            LOCK.notify();
            System.out.println("Hello from method 2 even after notifying");
        }
    }
}
