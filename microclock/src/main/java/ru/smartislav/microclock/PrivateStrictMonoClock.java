package ru.smartislav.microclock;

public final class PrivateStrictMonoClock extends Clock {
  private final Clock backend;
  private long lastMicros = Long.MIN_VALUE;

  public PrivateStrictMonoClock(Clock backend) {
    this.backend = backend;
  }

  public long micros() {
    lastMicros = Math.max(backend.micros(), lastMicros + 1);
    return lastMicros;
  }
}
