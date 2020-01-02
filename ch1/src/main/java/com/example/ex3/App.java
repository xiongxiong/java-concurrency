package com.example.ex3;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        FileSearch searcher = new FileSearch("/home/wonderbear", "App.java");
        Thread thread = new Thread(searcher);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}