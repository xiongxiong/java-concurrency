package com.example.ex6;

import java.util.Date;

public class Task implements Runnable {

    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Executed at: %s\n", name, new Date());
    }
}