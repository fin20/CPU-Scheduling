import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler {

  // TODO
  private Queue<Process> readyQueue;
  private int initialBurstEstimate;
  private double alphaBurstEstimate;

  /**
   * Initializes the scheduler from the given parameters
   */
  @Override
  public void initialize(Properties parameters) {
    initialBurstEstimate = Integer.parseInt(parameters.getProperty("initialBurstEstimate"));
    alphaBurstEstimate = Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
    readyQueue = new LinkedList<>();
  }

  public double expAvg(int currentBurst, int prevBurst){
    return alphaBurstEstimate * currentBurst + (1 - alphaBurstEstimate) * prevBurst;
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  @Override
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    process.setPriority((int) expAvg(process.getRecentBurst(), process.getPriority()));
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

    Process nextProcess = readyQueue.peek();
    for (Process process : readyQueue) {
      if (process.getPriority() < nextProcess.getPriority()) {
        nextProcess = process;
      }
    }

    readyQueue.remove(nextProcess);

    return nextProcess;

    //return readyQueue.poll();
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
