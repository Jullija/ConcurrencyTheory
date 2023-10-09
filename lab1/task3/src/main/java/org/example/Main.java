package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int ILOSC = 100;
    public static int PRODUCERS = 5;
    public static int CONSUMERS = 5;

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < PRODUCERS; i++){
            Thread thread = new Thread(new Producer(buffer));
            list.add(thread);
            thread.start();
        }

        for (int i = 0; i < CONSUMERS; i++){
            Thread thread = new Thread(new Consumer(buffer));
            list.add(thread);
            thread.start();
        }


        list.forEach(thread ->{
            try{
                thread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        });
    }
}