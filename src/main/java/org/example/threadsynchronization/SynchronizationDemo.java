package org.example.threadsynchronization;

public class SynchronizationDemo {
    private static int counter1 = 0;
    private static int counter3 = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++)
                increment1();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++)
                increment2();
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                increment3();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(counter1 +" -- " + counter3);
    }

//    static synchronized method
    private static synchronized void increment1() {
        counter1++;
    }

//    static method, synchronized block
//    For static methods use synchronized(classname.class)
//    For not static methods, use synchronized(this)
    private static void increment2(){
        synchronized(SynchronizationDemo.class){
            counter1++;
        }
    }

//    static synchronized method
//    increment1() and increment3() methods use same lock.
//    When a thread acquires lock on increment1() method,
//    it does not let any other thread to enter increment3() method
//    Because the lock is same.
    private static synchronized void increment3() {
        counter3++;
    }
}
