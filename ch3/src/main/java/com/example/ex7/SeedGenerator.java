package com.example.ex7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SeedGenerator implements Runnable {

    private CompletableFuture<Integer> resultCommunicator;

    public SeedGenerator(CompletableFuture<Integer> completable) {
        this.resultCommunicator = completable;
    }

    @Override
    public void run() {
        System.out.printf("SeedGenerator: Generating seed...\n");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int seed = (int)Math.rint(Math.random() * 10);
        System.out.printf("SeedGeneartor: Seed generated: %d\n", seed);
        resultCommunicator.complete(seed);
    }
}