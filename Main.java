import queue.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

  
   public static void main(String[] args) {
  NewQueue<Process> readyQueue = new LinkedQueue<Process>();

  Process process[]= new Process[100];
  Process processTest = new Process(0, 1);


  Random rand = new Random(); // a pseudo-random number generator
  rand.setSeed(System.currentTimeMillis()); // use current time as a seed

  int n = 100; // number of processes


  // fill the queue with pseudo-random numbers from 0 to 99, inclusive
  for (int i = 0; i < n; i++){
    process[i] = new Process(rand.nextInt(200), 1);
    readyQueue.enqueue(process[i]);
    process[i].startTime = (double) System.nanoTime();
  }

  while (readyQueue.size() != 0){
    processTest = readyQueue.first();

    // test for even
    if (processTest.content % 2 == 0){
      if (processTest.counter == 3){
        readyQueue.dequeue();
        processTest.status = "Done";
        processTest.endTime = (double) System.nanoTime();
      } else {
        readyQueue.dequeue();
        readyQueue.enqueue(processTest);
        processTest.counter++;
      }
    } else {

      // test for prime
      boolean flag = false;
      for (int i = 2; i <= processTest.content/2; ++i) {
      // condition for nonprime number
        if (processTest.content % i == 0) {
         flag = true;
          break;
        }
      }
      if (!flag){ // it is prime
        readyQueue.dequeue();
        processTest.status = "Done";
        processTest.endTime = (double) System.nanoTime();
      } else {

        // it is odd
        if (processTest.counter == 2){
          readyQueue.dequeue();
          processTest.status = "Done";
          processTest.endTime = (double) System.nanoTime();
        } else {
          processTest.counter++;
        }
      }
    }
  }

  double totalRunningTime = 0;
  double largestRunningTime = 0;
  double smallestRunningTime = 0 ;

  int largestKey = 0;
  int smallestKey =0;
  double time;
  
  // writing the results to a file
  try {
      
    BufferedWriter writer = new BufferedWriter(new FileWriter
    ("log_table_output.txt"));
    
    writer.write("index\t\tpid\t\tvisits\tstart time(nano sec)\tend time(nano sec)\t\tstatus\n");

  for (int i = 0; i < n ; i++){
    writer.write(i + " \t\t" + process[i].content + "\t\t" + process[i].counter +
     "\t\t" + process[i].startTime + "\t\t" + process[i].endTime + "\t\t\t" + 
     process[i].status + "\n");

    time = process[i].endTime - process[i].startTime;


    if ((time) > largestRunningTime){
      largestRunningTime = time;
      largestKey = process[i].content;

    }

    if ((time) < smallestRunningTime){
      smallestRunningTime = time;
      smallestKey = process[i].content;
    }

    totalRunningTime += (time);
   }

  double runningAveTime = totalRunningTime / n;

  writer.write("average running time (nano sec)=\t" + runningAveTime + "\n");
  writer.write("key with the smallest time=\t" + smallestKey + "\n");
  writer.write("key with the largest time=\t" + largestKey + "\n");
 
  //  writer.close();
    writer.close();
  } catch (IOException e) {
    e.printStackTrace();
  }
  }
}
  
  
class Process{
  public int counter = 1;
  public int content = 0;
  public double startTime = 0;
  public double endTime = 0;
  public String status;

  public Process (int content, int counter){
    this.content = content;
    this.counter = counter;
  }
}

  
