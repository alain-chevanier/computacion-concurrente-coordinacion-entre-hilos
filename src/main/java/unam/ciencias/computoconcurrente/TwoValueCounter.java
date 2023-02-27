package unam.ciencias.computoconcurrente;

public class TwoValueCounter extends FixedValueCounter {
  public TwoValueCounter() {
    super();
  }

  @Override
  public int getAndIncrement() {
    return 0;
  }

  @Override
  public int getAndDecrement() {
    return 0;
  }
}
