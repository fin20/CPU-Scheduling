import java.util.*;

/**
 * Feedback Round Robin Scheduler
 * 
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler {

  // TODO
  private int timeQuantum;
  private PriorityQueue<Process> readyQueue;

  private List<Integer> remainingTime;

  @Override
  public void initialize(Properties parameters) {
    timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
    readyQueue = new PriorityQueue<>();
    remainingTime = new ArrayList<>();
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  @Override
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO

    int priority = process.getPriority();

    if (usedFullTimeQuantum) {
      priority++;
      process.setPriority(priority);
    }

    readyQueue.offer(process);

    int timeSlice = timeQuantum;
    for (int i = 0; i < priority; i++) {
      timeSlice *= 2;
    }
    remainingTime.add(timeSlice);

    //retur

  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  @Override
  public Process schedule() {


    //TODO

    Process nextProcess = readyQueue.poll();

    if (nextProcess != null) {
      int timeSlice = remainingTime.get(0);
      timeSlice -= timeQuantum;
      remainingTime.set(0, timeSlice);


      if (timeSlice <= 0) {
        int priority = nextProcess.getPriority();
        priority++;
        nextProcess.setPriority(priority);
        remainingTime.remove(0);
      }
    }

    //return null;

    return nextProcess;
  }

  /**
   * Returns the time quantum of this scheduler
   * or -1 if the scheduler does not require a timer interrupt.
   */
  @Override
  public int getTimeQuantum() {
    return timeQuantum;
  }

  /**
   * Returns whether the scheduler is preemptive
   */
  @Override
  public boolean isPreemptive() {
    return true;
  }
}
