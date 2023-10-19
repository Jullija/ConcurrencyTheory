package org.example;

public class Main{
    static int ILOSC = 5;
    static int ILOSCWATKOW = 5;

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread[] consumerThreads = new Thread[ILOSCWATKOW];
        Thread[] producerThreads = new Thread[ILOSCWATKOW];

        for (int i = 0; i < ILOSC; i++ ) {
            producerThreads[i] = new Thread(new Producer(i, buffer));
            consumerThreads[i] = new Thread(new Consumer(i, buffer));
            producerThreads[i].start();
            consumerThreads[i].start();
        }

        for (int i = 0; i < ILOSC; i++){
            try {
                producerThreads[i].join();
                consumerThreads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}