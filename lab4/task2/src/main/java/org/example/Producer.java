package org.example;

import java.util.Random;

public class Producer implements Runnable{
    private Buffer buffer;
    private int id;
    private Random random;
    private int M;


    public Producer(Buffer buffer, int id, int M){
        this.buffer = buffer;
        this.id = id;
        random = new Random();
        this.M = M;
    }


    @Override
    public void run() {
        while (true){
            int rand = random.nextInt(M-1) + 1;
            try {
                buffer.produce(id, rand);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}