package org.example.otherConcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Scraper {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            executors.execute(new Runnable() {
                public void run() {
                    ScrapingService.INSTANCE.scrape();
                }
            });
        }
        executors.shutdown();
    }

    enum ScrapingService{
        INSTANCE;
        private Semaphore semaphore = new Semaphore(5);
        public void scrape(){
            try {
                semaphore.acquire();
                invokeScarapeBot();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally{
                semaphore.release();
            }
        }

        private void invokeScarapeBot() {
            try {
                System.out.println("Scrape Data Executing...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
