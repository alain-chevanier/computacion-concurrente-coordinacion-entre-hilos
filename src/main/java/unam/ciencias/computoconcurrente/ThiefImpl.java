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
  public void tryToFindPassword() {}

  @Override
  public int getId() {
    return thiefId;
  }

  @Override
  public int getTries() {
    return 0;
  }

  @Override
  public String toString() {
    return "ThiefImpl{" + "thiefId=" + thiefId + ", tries=" + tries + '}';
  }
}
