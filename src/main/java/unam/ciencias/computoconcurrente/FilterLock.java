package unam.ciencias.computoconcurrente;

import java.util.ArrayList;
import java.util.List;

public class FilterLock implements Lock {

  private int threads;
  private List<VolatileField<Integer>> level;
  private List<VolatileField<Integer>> lastToEnter;

  public FilterLock(int threads) {
    this.threads = threads;
    this.level = new ArrayList<>(threads);
    this.lastToEnter = new ArrayList<>(threads);
    for (int i = 0; i < this.threads; i++) {
      level.add(new VolatileField<>(-1));
      lastToEnter.add(new VolatileField<>(-1));
    }
  }

  @Override
  public void lock() {
    int threadId = ThreadID.get();

    for (int currentLevel = 0; currentLevel < level.size() - 1; currentLevel++) {
      level.get(threadId).setValue(currentLevel);
      lastToEnter.get(currentLevel).setValue(threadId);
      boolean isThereAThreadInAHigherLevel;
      do {
        isThereAThreadInAHigherLevel = false;
        for (int otherThreadId = 0; otherThreadId < this.threads; otherThreadId++) {
          if (otherThreadId == threadId) {
            continue;
          }
          if (level.get(otherThreadId).getValue() >= currentLevel) {
            isThereAThreadInAHigherLevel = true;
            break;
          }
        }
      } while (lastToEnter.get(currentLevel).getValue() == threadId
          && isThereAThreadInAHigherLevel);
    }
  }

  @Override
  public void unlock() {
    level.get(ThreadID.get()).setValue(-1);
  }
}
