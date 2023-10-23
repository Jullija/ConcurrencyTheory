package org.example;

public class Main {
    public static void main(String[] args) {
        int phNum = 5;
        Monitor monitor = new Monitor(phNum - 1);
        Philosopher[] philosophers = new Philosopher[phNum];

        for (int i = 0; i < phNum; i++){
            philosophers[i] = new Philosopher(i, monitor);
            new Thread(philosophers[i]).start();
        }
    }
}