package org.example;

public class RunnableThreadExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Demo1());
        Thread t2 = new Thread(new Demo2());
        Thread t3 = new Thread(() -> {
                for (int i = 0; i < 30; i++)
                    System.out.println("Thread 3: " +i);
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

class Demo1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 30; i++)
            System.out.println("Thread 1: " +i);
    }
}

class Demo2 implements Runnable{
    public void run() {
        for (int i = 0; i < 30; i++)
            System.out.println("Thread 2: " +i);
    }
}
