package com.example.ex4;

import java.util.concurrent.Phaser;

public class App {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("~/Downloads", "pdf", phaser);
        FileSearch apps = new FileSearch("~/Programming", "pdf", phaser);
        FileSearch documents = new FileSearch("~/Personal", "pdf", phaser);
        Thread systemThread = new Thread(system, "System");
        systemThread.start();
        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();
        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();

        try{
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Terminated: " + phaser.isTerminated());
    }
}