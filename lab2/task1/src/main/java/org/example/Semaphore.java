package org.example;

public class Semaphore {

    private boolean unlocked;
    public Semaphore() {
        this.unlocked = true;
    }

    synchronized public void lock() {
        while(!unlocked){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.unlocked = false;
    }

    synchronized public void unlock() {
        this.unlocked = true;
        notifyAll();
    }
}
