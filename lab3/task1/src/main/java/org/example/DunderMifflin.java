package org.example;

public class DunderMifflin implements Runnable{

    private final PrinterMonitor printerMonitor;
    private final int id;

    public DunderMifflin(PrinterMonitor printerMonitor, int id) {
        this.printerMonitor = printerMonitor;
        this.id = id;
    }

    private void newJob(){
        System.out.println("New job from " + this.id);
    }

    private int getPrinter(){
        int printerID = printerMonitor.usePrinter();
        System.out.println(this.id + " got printer " + printerID);
        return printerID;
    }

    private void printing(int printerID){
        long sleepTime = 5000;
        System.out.println(this.id + " is using printer " + printerID + " for 5 seconds");
        try{
            Thread.sleep(sleepTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void freePrinter(int printerID){
        printerMonitor.returnPrinter(printerID);
        System.out.println("Printer " + printerID + " is returned");
    }


    @Override
    public void run() {
        int printerID;
        while (true){
            newJob();
            printerID = getPrinter();
            printing(printerID);
            freePrinter(printerID);
        }
    }
}
