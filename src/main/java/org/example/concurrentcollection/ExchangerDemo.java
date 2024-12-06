package org.example.concurrentcollection;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread t1 = new Thread(new ThreadOne(exchanger));
        Thread t2 = new Thread(new ThreadTwo(exchanger));
        t1.start();
        t2.start();
    }

    static class ThreadOne implements Runnable {

        private final Exchanger<Integer> exchanger;

        public ThreadOne(Exchanger<Integer> exchanger){
            this.exchanger = exchanger;
        }

        public void run() {
            try {
                int dataToSend = 10;
                System.out.println("ThreadOne is sending the number: " + dataToSend);
                int dataReceived = exchanger.exchange(dataToSend);
                System.out.println("ThreadOne received the number: " + dataReceived);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class ThreadTwo implements Runnable {

        private final Exchanger<Integer> exchanger;

        public ThreadTwo(Exchanger<Integer> exchanger){
            this.exchanger = exchanger;
        }

        public void run() {
            try {
                Thread.sleep(200);
                int dataToSend = 20;
                System.out.println("ThreadTwo is sending the number: " + dataToSend);
                int dataReceived = exchanger.exchange(dataToSend);
                System.out.println("ThreadTwo received the number: " + dataReceived);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
