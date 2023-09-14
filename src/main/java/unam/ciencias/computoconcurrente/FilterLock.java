package unam.ciencias.computoconcurrente;

import java.util.ArrayList;
import java.util.List;

public class FilterLock implements Lock {

  private int threads;

  public FilterLock(int threads) {
    this.threads = threads;
  }

  @Override
  public void lock() {
    int threadId = ThreadID.get();


  }

  @Override
  public void unlock() {
    int threadId = ThreadID.get();
  }
}
