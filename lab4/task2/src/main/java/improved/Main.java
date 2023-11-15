package improved;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main {
    public static int threadsNumber = 100;
    public static int bufferSize = 10000;

    public static Consumer[] consumers = new Consumer[threadsNumber];
    public static Producer[] producers = new Producer[threadsNumber];

    public static Thread[] consumersTh = new Thread[threadsNumber];
    public static Thread[] producersTh = new Thread[threadsNumber];

    public static void main(String[] args) throws InterruptedException, IOException {
        Buffer buffer = new Buffer(bufferSize);

        int j = 0;
        while (j < threadsNumber){
            consumers[j] = new Consumer(buffer, bufferSize, j);
            producers[j] = new Producer(buffer, bufferSize, j);
            consumersTh[j] = new Thread(consumers[j]);
            producersTh[j] = new Thread(producers[j]);

            producersTh[j].start();
            consumersTh[j].start();
            j++;
        }

        sleep(60000);
        printAverageTimes();
    }

    public static void printAverageTimes() throws IOException {
        System.out.println("******");
        List<List<Long>> getTimes = new ArrayList<>();
        List<List<Long>> putTimes = new ArrayList<>();
        for (int i = 0; i <= bufferSize; i++){
            getTimes.add(new ArrayList<>());
            putTimes.add(new ArrayList<>());
        }
        for (int i = 0; i < threadsNumber; i++){
            for (int j = 0; j <= bufferSize; j++){
                getTimes.get(j).addAll(consumers[i].timesList.get(j));
                putTimes.get(j).addAll(producers[i].timesList.get(j));
            }
        }

        List<Double> getMeans = new ArrayList<>();
        List<Double> putMeans = new ArrayList<>();
        for (int i = 0; i <= bufferSize; i++){
            long getSum = 0;
            long putSum = 0;
            for (Long val : getTimes.get(i)){
                getSum += val;
            }
            for (Long val : putTimes.get(i)){
                putSum += val;
            }
            double getMean = (double) (!getTimes.get(i).isEmpty() ? getSum / getTimes.get(i).size() : 0);

            double putMean = (double) (!putTimes.get(i).isEmpty() ? putSum / putTimes.get(i).size() : 0);

            getMeans.add(getMean);
            putMeans.add(putMean);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("times2.csv", true));
        for (int i = 1; i < bufferSize; i++){
            writer.write("get4condbool,"+bufferSize+","+threadsNumber+","+i+","+getMeans.get(i)+"\n");
            System.out.println("get4condbool,"+bufferSize+","+threadsNumber+","+i+","+getMeans.get(i));
        }

        for (int i = 1; i < bufferSize; i++){
            writer.write("put4condbool,"+bufferSize+","+threadsNumber+","+i+","+putMeans.get(i)+"\n");
            System.out.println("put4condbool,"+bufferSize+","+threadsNumber+","+i+","+putMeans.get(i));
        }

        writer.close();

    }


}
