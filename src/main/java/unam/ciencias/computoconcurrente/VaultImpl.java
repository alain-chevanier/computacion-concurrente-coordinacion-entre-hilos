package unam.ciencias.computoconcurrente;

public class VaultImpl implements Vault {
  private int password;
  private boolean passwordFound;
  private Lock lock;

  public VaultImpl(int password, Lock lock) {
    this.password = password;
    this.passwordFound = false;
    this.lock = lock;
  }

  public boolean isPassword(int guess) {

    return false;
  }

  public boolean isPasswordFound() {
    return passwordFound;
  }
}
