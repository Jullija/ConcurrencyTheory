package org.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{
    private int id;
    private Forks forks;

    public Philosopher(int id, Forks forks){
        this.id = id;
        this.forks = forks;
    }

    private void think() throws InterruptedException {
        Thread.sleep(5000);
    }

    private void eat() throws InterruptedException {
        Thread.sleep(4000);
    }


    @Override
    public void run() {
        try{
            while (true){
                think();
                System.out.println("Philosopher " + this.id + " is thinking about food.");
                forks.takeForks(this.id);
                System.out.println("Philosopher " + this.id + " has both forks and is eating.");
                eat();
                forks.returnForks(this.id);
                System.out.println("Philosopher " + this.id + " is returning the forks.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
