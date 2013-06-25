package ru.smartislav.microclock;

public final class NonThreadSafeWeaklyMonotonicMicroClockWrapper extends MicroClock {
  private final MicroClock backend;
  private long lastMicros = Long.MIN_VALUE;

  public NonThreadSafeWeaklyMonotonicMicroClockWrapper(MicroClock backend) {
    this.backend = backend;
  }

  public long micros() {
    lastMicros = Math.max(backend.micros(), lastMicros);
    return lastMicros;
  }
}
