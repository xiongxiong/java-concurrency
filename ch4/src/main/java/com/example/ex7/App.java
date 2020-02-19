package com.example.ex7;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();

        Task task = new Task();
        System.out.println("Main: Executing the Task\n");
        Future<String> result = executor.submit(task);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: Canceling the Task.");
        result.cancel(true);

        System.out.printf("Main: Cancelled: %s\n", result.isCancelled());
        System.out.printf("Main: Done: %s\n", result.isDone());

        executor.shutdown();
        System.out.println("Main: The executor has finished.");
    }
}