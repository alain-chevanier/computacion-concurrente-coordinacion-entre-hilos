package unam.ciencias.computoconcurrente;

public class FixedValueCounter implements Counter {
  protected volatile int value;
  protected ThreadLocal<Integer> iteration;
  protected int rounds;
  protected final int valueToBeReached;

  public FixedValueCounter(int valueToBeReached) {
    this.value = 0;
    this.iteration = ThreadLocal.withInitial(() -> 0);
    this.rounds = 0;
    this.valueToBeReached = valueToBeReached;
  }

  public void setIteration(int iteration) {
    this.iteration.set(iteration);
  }

  public void setRounds(int rounds) {
    this.rounds = rounds;
  }

  @Override
  public int getAndIncrement() {
    int id = ThreadID.get();
    int it = iteration.get();

    // TODO: implementar aquí la lógica

    return 0;
  }

  @Override
  public int getAndDecrement() {
    return 0;
  }

  @Override
  public int getValue() {
    return this.value;
  }
}
