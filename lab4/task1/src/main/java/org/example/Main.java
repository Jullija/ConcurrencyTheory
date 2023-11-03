package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    // id = 0 -> producer
    // id = 1 -> consumer
    // id > 1 -> processer


    public static void main(String[] args) {
        int processorsNum = 5;
        int bufferSize = 10;

        Buffer buffer = new Buffer(bufferSize);;
        List<Thread> processors = new ArrayList<>();
        Process producer = new Process(buffer, 0, -1, x -> 0);
        Process consumer = new Process(buffer, -1, processorsNum, x -> -1);
        processors.add(new Thread(producer));
        IntStream.range(1, processorsNum + 1)
                .mapToObj(i -> new Thread(new Process(buffer, i, i - 1, x -> x + 1))).forEach(processors::add);
        processors.add(new Thread(consumer));


        processors.forEach(Thread::start);
        processors.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }




}