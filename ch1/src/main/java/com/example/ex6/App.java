package com.example.ex6;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class App {
    
    public static void main(String[] args) {
        Deque<Event> deque = new ConcurrentLinkedDeque<Event>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }
}