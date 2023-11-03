package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ImprovedBuffer implements Buffer{
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition firstProducerCondition;
    private final Condition restProducersCondition;
    private final Condition firstConsumerCondition;
    private final Condition restConsumersCondition;

    private final int M;
    private int occupied = 0;

    public ImprovedBuffer(int M){
        this.M = M;
        this.firstConsumerCondition = lock.newCondition();
        this.firstProducerCondition = lock.newCondition();
        this.restConsumersCondition = lock.newCondition();
        this.restProducersCondition = lock.newCondition();
    }


    public void produce(int id, int howManyItemsProduce) throws InterruptedException {
        lock.lock();
        try{
            while (lock.hasWaiters(firstProducerCondition)){
                restProducersCondition.await();
            }
            while (M - occupied < howManyItemsProduce){
                firstProducerCondition.await();
            }

            occupied += howManyItemsProduce;
            System.out.println("Producer " + id + " produced " + howManyItemsProduce);
            firstConsumerCondition.signal();
            restProducersCondition.signal();


        }finally{
            lock.unlock();
        }
    }


    public int consume(int id, int howManyItemsConsume) throws InterruptedException {
        lock.lock();
        try{
            while (lock.hasWaiters(firstConsumerCondition)){
                restConsumersCondition.await();
            }

            while (howManyItemsConsume > occupied){
                firstConsumerCondition.await();
            }

            occupied -= howManyItemsConsume;
            System.out.println("Consumer " + id + " consumed " + howManyItemsConsume);
            restConsumersCondition.signal();
            firstProducerCondition.signal();
            return howManyItemsConsume;

        }finally {
            lock.unlock();
        }
    }






}
