package unam.ciencias.computoconcurrente;

public class ThiefBruteForce implements Thief {
  private Vault vault;
  private int guessUpperBound;
  private int participants;
  private int thiefId;
  private int tries;

  public ThiefBruteForce(Vault vault, int guessUpperBound, int thiefId, int participants) {
    this.vault = vault;
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
    // System.out.println("Thief " + this.thiefId + " space [" + min + ", " + max + ")");
    for (int i = min; i < max; i++) {
      if (vault.isPasswordFound()) {
        break;
      }
      Thread current = Thread.currentThread();
      if (current.isInterrupted()) {
        // System.out.println("Thief " + thiefId + " was caught");
        break;
      }
      int guess = i;
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
}
