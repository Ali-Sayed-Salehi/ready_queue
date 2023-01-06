# ready_queue
This program simulates the operating system's ready queue using a queue data structure

## How It Is Done
The operating system (OS) manages the processes (i.e. running programs) using a queue called the ready queue. Processes that are ready to execute are inserted at the tail of the queue to wait for the availability of the CPU. When the CPU becomes available, the process that is stored at the head of the queue is selected for execution. Each node of the queue stores the unique id of each process (i.e. pid) and a reference to the next node. In addition to the ready queue, a process table is used to maintain state information about each process.

In this program, we simulate the behaviour of a multitasking OS by implementing a ready queue in Java. Each node will store an integer key that will represent the pid of a process. 

Initially, the queue will be filled with 100 keys chosen at random using the Java.util.Random class. Every time a key is inserted for the first time in the queue we record its start time in a log table indexed 0..99. Once all the keys have been inserted into the queue then we begin removing them one by one. 

If a removed key value is a **prime number** then it terminates its execution immediately. If the key value is **odd (but not prime)** then it is inserted a second time in the queue and will terminate only after its second visit on the queue. If the key value is **even (but not prime)** then it has to visit the queue two more times before terminating. When a key is terminated, the end time will be recorded in its log table entry.

The log table also shows other useful state information about each key such as the running status, the number of visits in the queue, etc. We assume that the process of inserting and removing a key from the ready queue represents some kind of cpu burst cycle. 

When the queue is exhausted, we compute the following statistics : 
1. The average running time for all keys
2. The key that has the smallest running time
3. The key that has the largest running time

## Results
The text file "log_table_output.txt" shows the results.
