package org.example;

public class Philosopher implements Runnable{

    private final int id;
    private final Monitor monitor;

    public Philosopher(int id, Monitor monitor) {
        this.id = id;
        this.monitor = monitor;
    }


    public void pickUpForks() throws InterruptedException {
        monitor.getWaiter().requestFork();

        synchronized (monitor.getLeftFork(this.id)){
            System.out.println("Philosopher " + this.id + " picked up left fork.");
            synchronized (monitor.getRightFork(this.id)){
                System.out.println("Philosopher " + this.id + " picked up right fork.");
            }
        }
    }

    public void putDownForks() throws InterruptedException {
        synchronized (monitor.getRightFork(this.id)){
            System.out.println("Philosopher " + this.id + " put down right fork.");
            synchronized (monitor.getLeftFork(this.id)){
                System.out.println("Philosopher " + this.id + " put down left fork.");
            }
        }
        monitor.getWaiter().returnForks();
    }

    private void eat() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Philosopher " + this.id + " is eating.");
    }


    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(4000);
                pickUpForks();
                eat();
                putDownForks();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
