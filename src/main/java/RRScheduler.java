import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 * 
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {

  // TODO
  private int timeQuantum;
  private Queue<Process> readyQueue;


  @Override
  public void initialize(Properties parameters) {
    timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
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

    if (!readyQueue.isEmpty()){
      return readyQueue.poll();
    }
    return null;


  }

  @Override
  public boolean isPreemptive() {
    return false;
  }

  @Override
  public int getTimeQuantum() {
    return timeQuantum;
  }
}
