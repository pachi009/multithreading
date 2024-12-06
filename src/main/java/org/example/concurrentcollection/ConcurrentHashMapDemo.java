package org.example.concurrentcollection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        Map<String, String> cache = new ConcurrentHashMap<>();

        for(int i = 0; i < 10; i++) {
            int threadNum = i;
            new Thread(() -> {
                String key = "Key@" + threadNum;
                for(int j = 0; j<3; j++){
                    try {
                        String val = cache.get(key);
                        System.out.println(Thread.currentThread().getName() +" Key: " + key + " Value: " + val);
                        if (val == null) {
                            val = generateValue(key);
                            cache.put(key, val);
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }

    private static String generateValue(String key) throws InterruptedException {
        System.out.println(key +" is not present in the cache. So, going to compute-----");
        Thread.sleep(200);
        return "Value for " +key;
    }
}
