package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NaiveBuffer implements Buffer{

    private final int M;
    private int occupied = 0;
    private final Lock lock;
    private final Condition consumerCondition;
    private final Condition producerCondition;
    public NaiveBuffer(int M){
        this.M = M;
        this.lock = new ReentrantLock();
        this.consumerCondition = lock.newCondition();
        this.producerCondition = lock.newCondition();
    }




    public void produce(int id, int howManyItemsProduce) throws InterruptedException {
        lock.lock();
        try{
            while(M - occupied < howManyItemsProduce){
                producerCondition.await();
            }
            occupied += howManyItemsProduce;
            System.out.println("Producer " + id + " produced " + howManyItemsProduce);
            consumerCondition.signal();
            producerCondition.signal();

        }finally {
            lock.unlock();
        }
    }


    public int consume(int id, int howManyItemsConsume) throws InterruptedException {
        lock.lock();
        try{
            while(howManyItemsConsume > occupied){
                consumerCondition.await();
            }
            occupied -= howManyItemsConsume;
            System.out.println("Consumer " + id + " consumed " + howManyItemsConsume);
            producerCondition.signal();
            consumerCondition.signal();
            return howManyItemsConsume;

        } finally {
            lock.unlock();
        }

    }
}
