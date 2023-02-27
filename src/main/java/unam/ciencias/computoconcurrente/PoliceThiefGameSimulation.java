package unam.ciencias.computoconcurrente;

import java.util.ArrayList;
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
    this.thieves = new ArrayList<>(THIEVES);
    for (int i = 0; i < THIEVES; i++) {
      this.thieves.add(new ThiefBruteForce(vault, this.passwordUpperBound, i, THIEVES));
    }
  }

  public PoliceThiefGameWinner runSimulation() throws InterruptedException {
    List<Thread> thiefThreads = new ArrayList<>(this.thieves.size());
    ThreadID.resetInitialThreadIDTo(0);
    for (Thief thief : this.thieves) {
      thiefThreads.add(new Thread(thief::tryToFindPassword, thief.getId() + ""));
    }

    thiefThreads.forEach(Thread::start);

    for (int i = 0; i < VERIFICATIONS; i++) {
      Thread.sleep(this.simulationDurationInMS / VERIFICATIONS);
      if (vault.isPasswordFound()) {
        break;
      }
    }

    for (Thread t : thiefThreads) {
      if (t.isAlive()) {
        t.interrupt();
      }
    }

    for (Thread t : thiefThreads) {
      t.join();
    }

    if (this.vault.isPasswordFound()) {
      return PoliceThiefGameWinner.THIEF;
    } else {
      return PoliceThiefGameWinner.POLICE;
    }
  }

  public List<Thief> getThieves() {
    return thieves;
  }
}
