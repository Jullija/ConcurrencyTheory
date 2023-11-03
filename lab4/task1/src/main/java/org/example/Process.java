package org.example;

import java.util.Random;
import java.util.function.Function;

public class Process implements Runnable{

    private final int processTime;
    private final Buffer buffer;
    private final int bufferSize;
    private final int id;
    private final int prevId;
    private final Function<Integer, Integer> processFunction;


    public Process(Buffer buffer, int id, int prevId, Function<Integer, Integer> processFunction ) {
        this.buffer = buffer;
        this.processTime = (new Random()).nextInt(1000);
        this.id = id;
        this.prevId = prevId;
        this.bufferSize = buffer.getSize();
        this.processFunction = processFunction;


    }

    @Override
    public void run() {
        while (true){
            for (int i = 0; i < bufferSize; i++){
                try{
                    Data data = buffer.getData(i, prevId);
                    Thread.sleep(processTime);
                    data.setValue(processFunction.apply(data.getValue()));
                    buffer.finishProcess(i, id);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Worker " + this.id + " left pipeline in state " + buffer.getState());
            }
        }
    }
}
