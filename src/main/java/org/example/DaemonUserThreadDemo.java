package org.example;

public class DaemonUserThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 10; i++)
               System.out.println("User Thread: " +i);
        });

        Thread t2 = new Thread(() -> {
           for(int i = 0; i < 100; i++)
               System.out.println("Daemon Thread: " +i);
        });
        t2.setDaemon(true);

        t1.start();
        t2.start();
    }
}
