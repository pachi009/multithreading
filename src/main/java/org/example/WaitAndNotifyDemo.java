package org.example;

public class WaitAndNotifyDemo {
    private static Object LOCK = new Object();

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

    private static void method1() throws InterruptedException {
        synchronized(LOCK) {
            System.out.println("method1 started--------");
            LOCK.wait();
            System.out.println("method1 ended--------");
        }
    }

    private static void method2() {
        synchronized (LOCK) {
            System.out.println("method2 started--------");
            LOCK.notify();
            System.out.println("method2 ended--------");
        }
    }
}
