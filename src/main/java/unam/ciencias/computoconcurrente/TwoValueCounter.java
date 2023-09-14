package unam.ciencias.computoconcurrente;

public class TwoValueCounter extends FixedValueCounter {
  public TwoValueCounter() {
    super(2);
  }

  @Override
  public int getAndIncrement() {
    int threadId = ThreadID.get();
    int prevValue = 0;
    try {
      if ((iteration.get() == rounds - 1) && threadId == 1) {
        // 3. Cuando el hilo 1 esta por terminar el contador estaría en 99.
        // Debemos dejar que el otro hilo sobreescriba el valor a 1 antes de leerlo
        Thread.sleep(100);
      }

      if (iteration.get() == 1 && threadId == 0) {
        // 4. En este punto el hilo 1 sobreescribe el valor, es necesario
        // esperar a que el hilo 0 lo lea.
        Thread.sleep(100);
      }

      int tmp = value;
      prevValue = value;

      // 1. Si es la primera iteración, les doy tiempo para que ambos lean 0
      if (iteration.get() == 0) {
        Thread.sleep(50);
      }

      // 2. En este punto suponemos que ambos lean 0.
      // Ahora detenemos al hilo 0 y dejamos avanzar al hilo 1 hasta casi terminar
      if (iteration.get() == 0 && threadId == 0) {
        Thread.sleep(100);
      }

      if ((iteration.get() == rounds - 1) && threadId == 1) {
        // 5. Debemos esperar a que el hilo 0 termine antes de escribir el valor.
        Thread.sleep(500);
      }

      value = tmp + 1;
    } catch (InterruptedException e) {
      System.out.println("Se interrumpio el hilo!");
    }

    return prevValue;
  }

  @Override
  public int getAndDecrement() {
    return 0;
  }
}
