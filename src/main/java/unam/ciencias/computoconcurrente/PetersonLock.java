package unam.ciencias.computoconcurrente;

import java.util.List;

/**
 * This particular implementation of a lock uses the Peterson's Algorithm which only work for two
 * concurrent threads at most.
 */
public class PetersonLock implements Lock {

  private final boolean[] flag;
  private volatile int lastToArrive;

  public PetersonLock() {
    flag = new boolean[]{false, false};
  }

  @Override
  public void lock() { // acquire
    int threadId = ThreadID.get(); // 0, 1

    flag[threadId] = true;
    lastToArrive = threadId;

    // threadId = 0 -> 1-0 = 1
    // threadId = 1-> 1-1 = 0
    while (theOtherThreadWantsTheLock() && iWasTheLastToArrive()) {}
  }

  private boolean theOtherThreadWantsTheLock() {
    int threadId = ThreadID.get(); // 0, 1
    return flag[1 - threadId];
  }

  private boolean iWasTheLastToArrive() {
    int threadId = ThreadID.get(); // 0, 1
    return lastToArrive == threadId;
  }

  @Override
  public void unlock() {
    int threadId = ThreadID.get();
    flag[threadId] = false;
  }
}
