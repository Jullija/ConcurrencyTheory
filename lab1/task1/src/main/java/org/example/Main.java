package org.example;

// monitor związany z obiektem związany z metodą (jeden wątek sycnhronizowany A, drugi nie może wywołać B)
// jeden monitor - jeden obiekt
// do moitora może wejśc tylko jeden wątek
// wait - wątek w środku pozwala wchodzić innemu wątkowi, ten na którym jest wait zasypia
// notify - obudzić uspiony wątek
// lepiej while (!condition) niż if(!condition)

public class Main {
    public static void main(String[] args) {

        Counter counter = new Counter();

        MyThread t1 = new MyThread(counter, "t1");
        MyThread t2 = new MyThread(counter, "t2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(t1.printCounter());

    }
}