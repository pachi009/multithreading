package org.example.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchOccuranceTask extends RecursiveTask<Integer> {

    private final int[] arr;
    private final int start;
    private final int end;
    private final int searchElement;

    SearchOccuranceTask(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    @Override
    protected Integer compute() {
        if(end-start+1 > 25){
            int mid = (start + end)/2;
            SearchOccuranceTask task1 = new SearchOccuranceTask(arr, start, mid, searchElement);
            SearchOccuranceTask task2 = new SearchOccuranceTask(arr, mid+1, end, searchElement);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        } else
            return searchElementOccurance();
    }

    private Integer searchElementOccurance() {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if(arr[i] == searchElement)
                count++;
        }
        return count;
    }
}

class ForkJoinPooldemo{
    public static void main(String[] args) {
        int[] arr = new int[100];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10) +1;
        }
        int searchElement = random.nextInt(10) +1;
        SearchOccuranceTask searchTask = new SearchOccuranceTask(arr, 0, arr.length-1, searchElement);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        int frequency = pool.invoke(searchTask);
        System.out.println(Arrays.toString(arr));
        System.out.printf("Search Element %d found %d times", +searchElement, frequency);
        pool.shutdown();
    }
}