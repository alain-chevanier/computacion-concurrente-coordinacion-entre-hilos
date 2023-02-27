package unam.ciencias.computoconcurrente;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class PoliceThiefGameSimulationTest {
  final int SIMULATIONS = 10;
  final int DURATION_IN_MS = 10000;
  final int PASSWORD_UPPER_BOUND = 300000000;

  @Test
  @Timeout(DURATION_IN_MS * 11)
  void runSimulations() throws InterruptedException {
    Random random = new Random();
    int policeWinCount = 0;
    for (int i = 0; i < SIMULATIONS; i++) {
      int currentPassword = random.ints(0, PASSWORD_UPPER_BOUND).findFirst().getAsInt();
      System.out.println(
          "\n\nStarting simulation " + i + ". Password to be found " + currentPassword);
      PoliceThiefGameSimulation simulation =
          new PoliceThiefGameSimulation(currentPassword, PASSWORD_UPPER_BOUND, DURATION_IN_MS);
      PoliceThiefGameWinner winner = simulation.runSimulation();
      if (winner == PoliceThiefGameWinner.POLICE) {
        System.out.println("Policeman caught the thieves.");
        policeWinCount++;
      } else {
        System.out.println("Thieves escaped with the vault content.");
      }

      for (Thief t : simulation.getThieves()) {
        System.out.println(t);
      }
    }

    System.out.println("Simulations ended. Police wins " + policeWinCount + "/" + SIMULATIONS);

    assertThat(policeWinCount, is(greaterThan(0)));
    assertThat(policeWinCount, is(lessThan(SIMULATIONS)));
  }
}
