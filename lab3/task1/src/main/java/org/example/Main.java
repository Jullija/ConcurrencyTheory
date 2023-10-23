package org.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        PrinterMonitor printerMonitor = new PrinterMonitor(10);
        List<Thread> printingThreads = IntStream.range(0, 10).boxed().map(id -> new Thread(new DunderMifflin(printerMonitor, id))).collect(Collectors.toList());


        for (Thread printingThread : printingThreads) {
            printingThread.start();
        }
        for (Thread printingThread : printingThreads) {
            printingThread.join();
        }
    }
}