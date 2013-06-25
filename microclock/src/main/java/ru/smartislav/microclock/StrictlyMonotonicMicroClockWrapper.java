package ru.smartislav.microclock;

import java.util.concurrent.atomic.AtomicLong;

final class StrictlyMonotonicMicroClockWrapper extends MicroClock {
  private final MicroClock backend;
  private final AtomicLong lastMicros = new AtomicLong(Long.MIN_VALUE);

  StrictlyMonotonicMicroClockWrapper(MicroClock backend) {
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
