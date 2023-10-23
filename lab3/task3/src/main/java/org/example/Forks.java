package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Forks {

    private final int philosophersNum = 5;
    private Lock[] forks = new Lock[philosophersNum];
    private Condition[] canEat = new Condition[philosophersNum];

    public Forks(){
        for (int i = 0; i < philosophersNum; i++){
            forks[i] = new ReentrantLock();
            canEat[i] = forks[i].newCondition();
        }
    }

    public void takeForks(int philosopherID){
        int leftFork = philosopherID;
        int rightFork = (philosopherID + 1) % philosophersNum;

        forks[leftFork].lock();
        forks[rightFork].lock();
    }

    public void returnForks(int philosopherID){
        int leftFork = philosopherID;
        int rightFork = (philosopherID + 1) % philosophersNum;

        forks[leftFork].unlock();
        forks[rightFork].unlock();
    }

    public int getPhilosophersNum(){
        return this.philosophersNum;
    }


}
