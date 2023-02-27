package unam.ciencias.computoconcurrente;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

public class FiveValueCounterTest {
  final int EXECUTIONS = 100;
  final int ITERATIONS = 100;
  final int ACCEPTANCE_PERCENTAGE = 50;
  final int EXPECTED_VALUE = 5;

  @Test
  void reachFive()
      throws InterruptedException, InvocationTargetException, NoSuchMethodException,
          InstantiationException, IllegalAccessException {
    FixedValueCounterTestExecutor executor =
        new FixedValueCounterTestExecutor(
            EXECUTIONS, ITERATIONS, ACCEPTANCE_PERCENTAGE, EXPECTED_VALUE, FiveValueCounter.class);
    executor.executeTest();
  }
}
