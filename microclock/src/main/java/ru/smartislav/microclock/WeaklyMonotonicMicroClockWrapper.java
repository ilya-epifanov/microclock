package ru.smartislav.microclock;

import java.util.concurrent.atomic.AtomicLong;

final class WeaklyMonotonicMicroClockWrapper extends MicroClock {
  private final MicroClock backend;
  private final AtomicLong lastMicros = new AtomicLong(Long.MIN_VALUE);

  WeaklyMonotonicMicroClockWrapper(MicroClock backend) {
    this.backend = backend;
  }

  @Override
  public long micros() {
    long saved;
    long actual;
    do {
      saved = lastMicros.get();
      actual = backend.micros();
      if (saved == actual) {
        return actual;
      } else if (actual < saved) {
        return saved;
      } else {
        if (lastMicros.weakCompareAndSet(saved, actual)) {
          return actual;
        }
      }
    } while (true);
  }
}
