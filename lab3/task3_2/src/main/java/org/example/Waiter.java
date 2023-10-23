package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
    private final Lock lock = new ReentrantLock();
    private final Condition canEat;
    private int availableSeats;

    public Waiter(int maxSeats){
        this.availableSeats = maxSeats;
        this.canEat = lock.newCondition();
    }

    public void requestFork() throws InterruptedException {
        lock.lock();
        try{
            while (availableSeats == 0){
                canEat.await();
            }
            availableSeats--;
        } finally {
            lock.unlock();
        }
    }


    public void returnForks(){
        lock.lock();
        try{
            availableSeats++;
            canEat.signalAll();
        }finally{
            lock.unlock();
        }
    }

}
