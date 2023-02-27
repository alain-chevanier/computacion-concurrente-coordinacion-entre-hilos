package unam.ciencias.computoconcurrente;

public class FilterLock implements Lock {

  private int threads;

  public FilterLock(int threads) {
    this.threads = threads;
  }

  @Override
  public void lock() {}

  @Override
  public void unlock() {}
}
