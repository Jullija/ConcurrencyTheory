package org.example;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int M = 10;
        Buffer buffer = new Buffer(2*M);
        int producersNum = 5;
        int consumersNum = 5;
        List<Thread> producerThreads = IntStream.range(0, producersNum).boxed().map(id -> new Thread(new Producer(buffer, id, M))).collect(Collectors.toList());
        List<Thread> consumerThreads= IntStream.range(0, consumersNum).boxed().map(id -> new Thread(new Consumer(buffer, id, M))).collect(Collectors.toList());

        for (Thread prodThread : producerThreads) prodThread.start();

        for (Thread consThread : consumerThreads) consThread.start();




    }
}