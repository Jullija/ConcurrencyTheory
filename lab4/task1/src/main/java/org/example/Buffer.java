package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Buffer {
    private final List<ReentrantLock> locks = new ArrayList<>();
    private final List<Condition> conditions = new ArrayList<>();
    private final List<Integer> processIDs = new ArrayList<>(); // starts with -1 -1 -1 -1..
    private final List<Data> pipeline = new ArrayList<>();
    private final int size;

    public Buffer(int size){
        this.size = size;
        IntStream.range(0, size).forEach(i -> {
            pipeline.add(new Data(i, -1));
            ReentrantLock lock = new ReentrantLock();
            locks.add(lock);
            conditions.add(lock.newCondition());
            processIDs.add(-1);
        });
    }

    public int getSize(){
        return this.size;
    }

    public Data getData(int i, int prevID) throws InterruptedException {
        locks.get(i).lock();

        while (processIDs.get(i) != prevID){
            conditions.get(i).await();
        }

        return pipeline.get(i);
    }


    public void finishProcess(int i, int id){
        processIDs.set(i, id);
        conditions.get(i).signalAll();
        locks.get(i).unlock();
    }

    public String getState(){
        return pipeline.toString() + '\n' + processIDs;
    }


}
