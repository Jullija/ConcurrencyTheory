package org.example;

public class MyThread extends Thread{

    Counter counter;
    String name;

    MyThread(Counter counter, String name){
        this.counter = counter;
        this.name = name;
    }


    @Override
    public void run() {
        if(this.name.equals("t1")){
            for (int i = 0; i < 1000000; i++){
                counter.increment();
            }
        }

        if(this.name.equals("t2")){
            for (int i = 0; i < 1000000; i++){
                counter.decrement();
            }
        }
    }

    public String printCounter(){
        return counter.printCounter();
    }
}
