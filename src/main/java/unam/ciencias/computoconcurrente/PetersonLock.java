package unam.ciencias.computoconcurrente;

/**
 * This particular implementation of a lock uses the Peterson's Algorithm which only work for two
 * concurrent threads at most.
 */
public class PetersonLock implements Lock {

  public PetersonLock() {}

  @Override
  public void lock() {}

  @Override
  public void unlock() {}
}
