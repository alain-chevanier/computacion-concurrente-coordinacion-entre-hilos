package unam.ciencias.computoconcurrente;

import java.util.List;

/**
 * This particular implementation of a lock uses the Peterson's Algorithm which only work for two
 * concurrent threads at most.
 */
public class PetersonLock implements Lock {

  private List<VolatileField<Boolean>> flag;
  private volatile int victim;

  public PetersonLock() {
    flag = List.of(new VolatileField<>(false), new VolatileField<>(false));
  }

  @Override
  public void lock() {
    int threadId = this.getCurrentThreadId();

    flag.get(threadId).setValue(true);
    victim = threadId;

    while (flag.get(1 - threadId).getValue() && victim == threadId) {}
  }

  @Override
  public void unlock() {
    int threadId = this.getCurrentThreadId();
    flag.get(threadId).setValue(false);
  }

  private int getCurrentThreadId() {
    return Integer.parseInt(Thread.currentThread().getName()) % 2;
  }
}
