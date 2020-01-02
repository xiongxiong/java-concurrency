package com.example.ex5;

import java.util.Date;

public class App {

    public static void main(String[] args) {

        DataSourceLoader dsLoader = new DataSourceLoader();
        Thread thread1 = new Thread(dsLoader, "DataSourceThread");

        NetworkConnectionLoader ncLoader = new NetworkConnectionLoader();
        Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");

        try {
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}