package org.example;

public class Buffer {

    private boolean empty = true;
    private String message;


    public synchronized void put(String string) throws InterruptedException {
        while(!this.empty){
            this.wait();
        }
        this.empty = false;
        this.message = string;
        System.out.println("Buffer is full");
        this.notifyAll();

    }

    public synchronized String take() throws InterruptedException {
        while(empty){
            this.wait();
        }
        empty = true;
        System.out.println("buffer clear");
        this.notifyAll();
        return message;


    }
}
