package org.example;

public class Counter {
    private int num = 0;

    public synchronized void increment(){
        this.num += 1;
    }

    public synchronized void decrement(){
        this.num -= 1;
    }

    public String printCounter(){
        return String.valueOf(num);
    }

}
