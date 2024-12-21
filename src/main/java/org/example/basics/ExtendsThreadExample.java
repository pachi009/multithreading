package org.example.basics;

public class ExtendsThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new DemoThread1();
        Thread t2 = new DemoThread2();
        t1.start();
        t2.start();
    }
}

class DemoThread1 extends Thread {
    public void run(){
        for (int i = 0; i < 20; i++) {
            System.out.println("Thread 1: " +i);
        }
    }
}

class DemoThread2 extends Thread {
    public void run(){
        for (int i = 0; i < 20; i++) {
            System.out.println("Thread 2: " +i);
        }
    }
}