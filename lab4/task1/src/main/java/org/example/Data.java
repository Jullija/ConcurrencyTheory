package org.example;

public class Data {

    private final int id;
    private int value;



    public Data(int id, int value){
        this.id = id;
        this.value = value;
    }


    public int getValue(){
        return this.value;
    }

    public void setValue(int newVal){
        this.value = newVal;
    }

    @Override
    public String toString(){
        return ("ID: " + this.id + ", value " + this.value);
    }
}
