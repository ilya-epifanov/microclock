package ru.smartislav.microclock;

import java.util.concurrent.atomic.AtomicLong;

final class StrictMonoClock extends Clock {
  private final Clock backend;
  private final AtomicLong lastMicros = new AtomicLong(Long.MIN_VALUE);

  StrictMonoClock(Clock backend) {
    this.backend = backend;
  }

  @Override
  public long micros() {
    long saved;
    long actual;
    do {
      saved = lastMicros.get();
      actual = Math.max(backend.micros(), saved + 1);
      if (lastMicros.weakCompareAndSet(saved, actual))
        return actual;
    } while (true);
  }
}
