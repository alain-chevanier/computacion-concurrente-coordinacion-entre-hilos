package unam.ciencias.computoconcurrente;

import java.util.List;

/**
 * This particular implementation of a lock uses the Peterson's Algorithm which only work for two
 * concurrent threads at most.
 */
public class PetersonLock implements Lock {



  public PetersonLock() {
  }

  @Override
  public void lock() { // acquire
    int threadId = ThreadID.get(); // 0, 1


  }


  @Override
  public void unlock() {
    int threadId = ThreadID.get();
  }
}
