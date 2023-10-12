package org.example;

public class Decrement extends Thread{
    private Counter counter;

    Decrement(Counter counter){
        this.counter = counter;
    }

    public void run(){
        for (int i = 0; i < 100; i++){
            this.counter.decrement();
        }
    }

}
