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
    int prevValue = 0;
    /*  Misma implementación que TwoValueCounter, pero
     * el hilo 0 espera en su ronda rounds-4 el valor del
     * hilo 1 y escribe sus últimas 4 rondas.*/
    try {
      /* Dejamos que el hilo 0 corra todas sus rondas sin
       * interrupción hasta la última, donde tendrá que
       * esperar para leer el primer valor que
       * escriba el hilo 1
       */
      if (id == 0 && it == rounds -  (this.valueToBeReached - 1)) {
        Thread.sleep(200);
        /* Tiempo para que sí despierte el hilo 1 */
      }

      int tmp = value;
      prevValue = value;

      /* El hilo 0 deja que el hilo 1 lea el valor inicial.*/
      if (id == 0 && it == 0) Thread.sleep(10);

      /* El hilo 0 permite que el hilo 1 ejecute todas sus
       * rondas para ser el último en escribir en su última.
       */
      if (id == 0 && it == rounds -  (this.valueToBeReached - 1)) {
        Thread.sleep(200); // Más Tiempo por el sleep del hilo 1
      }

      /* El hilo 1 duerme después de leer el valor inicial,
       * permitiendo que el hilo 0 ejecute hasta su última ronda.
       */
      if (id == 1 && it == 0) {
        Thread.sleep(100);
      }

      value = tmp + 1;

      /* El hilo 1 duerme después de escribir el primer valor
       * para que el hilo 0 lo lea.
       */
      if (id == 1 && it == 0) {
        Thread.sleep(150);
        /* Esperamos un largo tiempo para garantizar que el hilo 0
         * deje de dormir y lo lea.
         */
      }

    } catch (InterruptedException ie) {
    }

    return prevValue;
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
