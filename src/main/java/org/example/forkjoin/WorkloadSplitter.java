package org.example.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class WorkloadSplitter extends RecursiveAction {

    private final int workload;
    WorkloadSplitter(int workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload > 16) {
            System.out.println("Workload is huge. Splitting the Action.... " +workload);
            int firstWorkload = workload/2;
            int secondWorkload = workload - firstWorkload;
            WorkloadSplitter actian1 = new WorkloadSplitter(firstWorkload);
            WorkloadSplitter actian2 = new WorkloadSplitter(secondWorkload);
            actian1.fork();
            actian2.fork();
        } else {
            System.out.println("Workload is within limits. Executing the Action.... " + workload);
        }
    }
}

class DemoWorkload{
    public static void main(String[] args) {
        WorkloadSplitter workloadSplitter = new WorkloadSplitter(128);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        forkJoinPool.invoke(workloadSplitter);
        forkJoinPool.shutdown();
    }
}