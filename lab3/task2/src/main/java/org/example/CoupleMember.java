package org.example;

import java.util.stream.IntStream;

public class CoupleMember implements Runnable{
    private final Integer coupleID;
    private final Character personID;
    private final Waiter waiter;

    CoupleMember(Waiter waiter, Integer coupleID, Character personID) {
        this.waiter = waiter;
        this.coupleID = coupleID;
        this.personID = personID;
    }

    private void mindOwnBusiness() {
        long time = 5000;
        System.out.format("Couple " + this.coupleID + " is doing something. -> person " + this.personID
                +"\n");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void askForTable() {
        System.out.format(this.coupleID + " is asking for a table.-> person " + this.personID
                +"\n");
        try {
            waiter.request(this.coupleID);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.format(this.coupleID + " got a table.-> person " + this.personID
                +"\n");
    }

    private void haveDinner() {
        long time = 5000;
        System.out.format(this.coupleID + "are eating.-> person " + this.personID
                +"\n" );
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goHome() {
        System.out.format(this.coupleID + " are leaving-> person " + this.personID
                +"\n");
        waiter.leave();
    }

    public void run() {
        while (true) {
            mindOwnBusiness();
            askForTable();
            haveDinner();
            goHome();
        }
    }
}
