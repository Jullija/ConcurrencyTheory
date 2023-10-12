package org.example;

public class Increment extends Thread{

    private Counter counter;

    Increment(Counter counter){
        this.counter = counter;
    }

    public void run(){
        for (int i = 0; i < 100; i++){
            this.counter.increment();
        }
    }


}
