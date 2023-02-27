package unam.ciencias.computoconcurrente;

import java.util.Random;

public class ThiefImpl implements Thief {
  private Vault vault;
  private Random random;
  private int guessUpperBound;
  private int participants;
  private int thiefId;
  private int tries;

  public ThiefImpl(Vault vault, int guessUpperBound, int thiefId, int participants) {
    this.vault = vault;
    this.random = new Random();
    this.guessUpperBound = guessUpperBound;
    this.thiefId = thiefId;
    this.participants = participants;
    this.tries = 0;
  }

  @Override
  public void tryToFindPassword() {
    int spaceSize = this.guessUpperBound / this.participants;
    int min = spaceSize * this.thiefId;
    int max = spaceSize * (this.thiefId + 1);
    System.out.println("Thief " + this.thiefId + " space [" + min + ", " + max + ")");
    while (true) {
      if (vault.isPasswordFound()) {
        break;
      }
      Thread current = Thread.currentThread();
      if (current.isInterrupted()) {
        System.out.println("Thief " + thiefId + " was caught");
        break;
      }
      int guess = random.ints(min, max).findFirst().getAsInt();
      vault.isPassword(guess);
      this.tries++;
    }
  }

  @Override
  public int getId() {
    return thiefId;
  }

  @Override
  public int getTries() {
    return this.tries;
  }

  @Override
  public String toString() {
    return "ThiefImpl{" + "thiefId=" + thiefId + ", tries=" + tries + '}';
  }
}
