package com.example.ex1;

public class App {

    public static void main(String[] args) {
        ParkingCash cash = new ParkingCash();
        ParkingStats stats = new ParkingStats(cash);
        System.out.printf("Parking Simulator\n");

        int numberSersors = 2 * Runtime.getRuntime().availableProcessors();
        Thread threads[] = new Thread[numberSersors];
        for (int i = 0; i < numberSersors; i++) {
            Sensor sensor = new Sensor(stats);
            Thread thread = new Thread(sensor);
            thread.start();
            threads[i] = thread;
        }

        for (int i = 0; i < numberSersors; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Number of cars: %d\n", stats.getNumberCars());
        System.out.printf("Number of motorcycles: %d\n", stats.getNumberMotorcycles());
        cash.close();
    }
}