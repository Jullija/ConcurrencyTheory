package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore();
        Counter counter =  new Counter(0, semaphore);

        Increment increment = new Increment(counter);
        Decrement decrement = new Decrement(counter);


        increment.start();
        decrement.start();

        increment.join();
        decrement.join();
    }
}