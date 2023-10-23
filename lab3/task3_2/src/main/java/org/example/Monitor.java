package org.example;

public class Monitor {

    private final Object[] forks;
    private final Waiter waiter;

    public Monitor(int maxSeats){
        forks = new Object[maxSeats + 1];
        for (int i = 0; i <= maxSeats; i++){
            forks[i] = new Object();
        }

        waiter = new Waiter(maxSeats);
    }

    public Object getLeftFork(int phID){
        return forks[phID];
    }

    public Object getRightFork(int phID){
        return forks[(phID + 1) % forks.length];
    }

    public Waiter getWaiter(){
        return this.waiter;
    }
}
