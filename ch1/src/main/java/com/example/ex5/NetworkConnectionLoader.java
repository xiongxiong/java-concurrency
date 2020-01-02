package com.example.ex5;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionLoader implements Runnable {

    public void run() {
        System.out.printf("Beginning network connection loading: %s\n", new Date());

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Network connection loading has finished: %s\n", new Date());
    }
}