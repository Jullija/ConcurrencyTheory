package org.example;

public class Counter {
    private int value;
    private Semaphore semaphore;

    Counter(int value, Semaphore semaphore){
        this.semaphore = semaphore;
        this.value = value;
    }


    public void increment(){
        this.semaphore.lock();
        this.value += 1;
        System.out.println(this.printCounter());
        this.semaphore.unlock();
    }

    public void decrement(){
        this.semaphore.lock();
        this.value -= 1;
        System.out.println(this.printCounter());
        this.semaphore.unlock();
    }

    public String printCounter(){
        return String.valueOf(value);
    }



}
