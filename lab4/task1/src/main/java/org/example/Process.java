package org.example;

public class Process implements Runnable{

    private int id;
    private Buffer buffer;

    public Process(int id, Buffer buffer){
        this.buffer = buffer;
        this.id = id;
    }



    @Override
    public void run() {

    }
}
