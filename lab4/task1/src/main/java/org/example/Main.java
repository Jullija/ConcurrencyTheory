package org.example;

public class Main {

    // id = 0 -> producer
    // id = 1 -> consumer
    // id > 1 -> processer
    public static void main(String[] args) {
        int N = 10;
        Buffer buffer = new Buffer(N);

        Producer producer = new Producer(buffer);
    }
}