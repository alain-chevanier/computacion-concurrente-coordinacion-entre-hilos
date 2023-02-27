package unam.ciencias.computoconcurrente;

import java.util.List;

public class PoliceThiefGameSimulation {
  public final int THIEVES = 2;
  public final int VERIFICATIONS = 100;

  private List<Thief> thieves;
  private Vault vault;
  private int simulationDurationInMS;
  private Lock lock;
  private int passwordUpperBound;

  public PoliceThiefGameSimulation(
      int password, int passwordUpperBound, int simulationDurationInMS) {
    this.lock = new PetersonLock();
    this.passwordUpperBound = passwordUpperBound;
    this.vault = new VaultImpl(password, this.lock);
    this.simulationDurationInMS = simulationDurationInMS;
  }

  public PoliceThiefGameWinner runSimulation() throws InterruptedException {
    return PoliceThiefGameWinner.POLICE;
  }

  public List<Thief> getThieves() {
    return List.of();
  }
}
