package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Waiter {

    private final Lock lock;
    private final Condition tableOccupied;
    private final HashMap<Integer, CoupleState> coupleState;
    private final HashMap<Integer, Condition> coupleCondition;
    private Integer coupleAtTheTableID;

    public Waiter() {
        this.lock = new ReentrantLock();
        this.tableOccupied = lock.newCondition();
        this.coupleCondition = new HashMap<>();
        this.coupleState = new HashMap<>();
        this.coupleAtTheTableID = -1;
    }

    void request(Integer coupleID) throws InterruptedException {
        lock.lock();

        if (!this.coupleState.keySet().contains(coupleID)) {
            this.coupleState.put(coupleID, CoupleState.BOTH_LEFT);
            this.coupleCondition.put(coupleID, lock.newCondition());
        }

        //one at the table, one left
        if (this.coupleState.get(coupleID) == CoupleState.ONE_LEFT) {
            this.coupleCondition.get(coupleID).await();
        }

        //one person arrived, waiting for the partner
        if (this.coupleState.get(coupleID) == CoupleState.BOTH_LEFT) {

            this.coupleState.put(coupleID, CoupleState.ONE_WAITING);
            if (!this.coupleAtTheTableID.equals(coupleID)) {
                this.coupleCondition.get(coupleID).await();
            }

            this.coupleState.put(coupleID, CoupleState.ENJOYING_DINNER);
        }


        else if (this.coupleState.get(coupleID) == CoupleState.ONE_WAITING) {

            while (this.coupleAtTheTableID != -1) {
                this.tableOccupied.await();
            }
            this.coupleAtTheTableID = coupleID;
            coupleCondition.get(coupleID).signal();
        }
        lock.unlock();
    }

    void leave() {
        lock.lock();
        if (this.coupleState.get(this.coupleAtTheTableID) == CoupleState.ENJOYING_DINNER) {
            this.coupleState.put(this.coupleAtTheTableID, CoupleState.ONE_LEFT);
        }
        else if (this.coupleState.get(this.coupleAtTheTableID) == CoupleState.ONE_LEFT) {
            this.coupleState.put(this.coupleAtTheTableID, CoupleState.BOTH_LEFT);
            this.coupleCondition.get(this.coupleAtTheTableID).signal();
            this.coupleAtTheTableID = -1;
            this.tableOccupied.signalAll();
        }
        lock.unlock();
    }
}






