package org.example;

public class Semaphore {
    private int curr;

    public Semaphore(int curr){
        this.curr = curr;
    }

    synchronized public void lock(){
        while (this.curr == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.curr -= 1;
    }


    synchronized public void unlock(){
        this.curr += 1;
        notifyAll();
    }

}
