package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);

        Consumer con1 = new Consumer(1,semaphore);
        Consumer con2 = new Consumer(2,semaphore);
        Consumer con3 = new Consumer(3,semaphore);


        con1.start();
        con2.start();
        con3.start();

        con1.join();
        con2.join();
        con3.join();
    }
}