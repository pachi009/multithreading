package org.example.concurrentcollection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0));
        Thread reader = new Thread(new ReadTask(list));
        Thread writer1 = new Thread(new WriteTask(list));
        Thread writer2 = new Thread(new WriteTask(list));
        Thread writer3 = new Thread(new WriteTask(list));

        reader.start();
        writer1.start();
        writer2.start();
        writer3.start();
    }
}

class ReadTask implements Runnable {

    private final List<Integer> list;

    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(list);
        }
    }
}

class WriteTask implements Runnable {

    private final List<Integer> list;
    private final Random random;

    public WriteTask(List<Integer> list) {
        this.list = list;
        random = new Random();
    }

    public void run() {
        try{
            while(true){
                Thread.sleep(1200);
                list.set(random.nextInt(list.size()), random.nextInt(10));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}