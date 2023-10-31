package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Buffer {

    int[] array;
    Condition[] conditions; //there will be condition for each
    Lock lock;
    public Buffer(int N){
        array = IntStream.generate(() -> -1)
                .limit(N)
                .toArray();
        lock = new ReentrantLock();
        conditions = new Condition[N];
    }

    public void step(int proc_id){
        while (true){
            try {
                lock.lock();

                for (int i = 0; i < array.length; i++ ){
                    switch (proc_id) {
                        case array.length -> {
                            if ()
                        }
                        default -> {
                            while (array[i] != proc_id - 1){
                                proc_id.await();
                            }
                        }
                    }
                }

            }finally{
                lock.unlock();
            }
        }
    }
}
