package org.example;

//changing -1 to 1
public class Producer {

    Buffer buffer;
    int id;

    public Producer(Buffer buffer){
        this.buffer = buffer;
        this.id = 0;
    }
}
