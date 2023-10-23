package org.example;

import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrinterMonitor {

    private final Lock lock;
    private final Condition notAllPrintersUsed;
    private HashSet<Integer> availablePrinters;


    public PrinterMonitor(int printersNum){
        this.lock = new ReentrantLock();
        this.notAllPrintersUsed = lock.newCondition();
        this.availablePrinters = IntStream.range(0, printersNum).boxed().collect(Collectors.toCollection(HashSet::new));
    }

    public Integer usePrinter(){
        lock.lock();
        try{
            while(this.availablePrinters.size() == 0) {
                notAllPrintersUsed.await();
            }
        }finally{
            Integer printer = this.availablePrinters.iterator().next();
            this.availablePrinters.remove(printer);
            lock.unlock();
            return printer;
        }
    }


    public void returnPrinter(int printer){
        lock.lock();
        this.availablePrinters.add(printer);
        try{
            if (this.availablePrinters.size() == 1){
                notAllPrintersUsed.signal();
            }
        } finally{
            lock.unlock();
        }
    }

}
