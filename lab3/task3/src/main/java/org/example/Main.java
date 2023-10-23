package org.example;

public class Main {
    public static void main(String[] args) {
        Forks forks = new Forks();
        Thread[] philosophersThreads = new Thread[forks.getPhilosophersNum()];

        for (int i = 0; i < forks.getPhilosophersNum(); i++){
           philosophersThreads[i] =  new Thread(new Philosopher(i, forks));
            philosophersThreads[i].start();
        }
    }
}