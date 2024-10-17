import java.util.*;

/**
 * Ideal Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler {

  // TODO
  private Queue<Process> readyQueue;
  /**
   * Initializes the scheduler from the given parameters
   */
  @Override
  public void initialize(Properties parameters) {
    readyQueue = new LinkedList<>();
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  @Override
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    readyQueue.offer(process);

  }


  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  @Override
  public Process schedule() {

    // TODO
    PriorityQueue<Process> sortedProcesses = new PriorityQueue<>(Comparator.comparingInt(Process::getNextBurst));
    sortedProcesses.addAll(readyQueue);

    //System.out.println(sortedProcesses);

    Process nextProcess = sortedProcesses.poll();
    readyQueue.remove(nextProcess);
    return nextProcess;
    //return null
    
  }

  @Override
  public boolean isPreemptive() {
    return false;
  }

  /**
   * Returns the time quantum of this scheduler
   * or -1 if the scheduler does not require a timer interrupt.
   */
  @Override
  public int getTimeQuantum() {
    return -1;
  }

}
