package org.example;

public class Consumer extends Thread{
    private int id;
    private Semaphore semaphore;

    public Consumer(int id, Semaphore semaphore){
        this.id = id;
        this.semaphore = semaphore;
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            semaphore.lock();
            System.out.println("Customer with id " + this.id + " took a basket. " + semaphore);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.unlock();
            System.out.println("Customer with id " + this.id + " returned a basket. " + semaphore);
        }
    }

}
