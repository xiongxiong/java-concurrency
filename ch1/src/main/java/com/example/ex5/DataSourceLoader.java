package com.example.ex5;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourceLoader implements Runnable {

    public void run() {
        System.out.printf("Beginning data source loading: %s\n", new Date());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Data source loading has finished: %s\n", new Date());
    }
}