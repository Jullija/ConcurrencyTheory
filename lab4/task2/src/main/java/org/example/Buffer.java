package org.example;

public interface Buffer {
    void produce(int id, int howManyItemsProduce) throws InterruptedException;
    int consume(int id, int howManyItemsConsume) throws InterruptedException;
}
