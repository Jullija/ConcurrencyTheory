package org.example;

import java.util.Random;

public class Consumer implements Runnable{

    private Buffer buffer;
    private int id;
    private Random random;
    private int M;


    public Consumer(Buffer buffer, int id, int M){
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
                buffer.consume(id, rand);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
