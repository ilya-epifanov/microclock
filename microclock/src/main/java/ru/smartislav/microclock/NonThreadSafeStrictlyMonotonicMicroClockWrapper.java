package ru.smartislav.microclock;

public final class NonThreadSafeStrictlyMonotonicMicroClockWrapper extends MicroClock {
  private final MicroClock backend;
  private long lastMicros = Long.MIN_VALUE;

  public NonThreadSafeStrictlyMonotonicMicroClockWrapper(MicroClock backend) {
    this.backend = backend;
  }

  public long micros() {
    lastMicros = Math.max(backend.micros(), lastMicros + 1);
    return lastMicros;
  }
}
