package org.example.concurrentcollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class SynchronisedCollection {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
           List<Integer> list = Collections.synchronizedList(new ArrayList());
//       List<Integer> list = new ArrayList<>();
        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 1000; i++) {
               list.add(i);
           }
       });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
    }
}

