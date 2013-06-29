package ru.smartislav.microclock;

public final class PrivateMonoClock extends Clock {
  private final Clock backend;
  private long lastMicros = Long.MIN_VALUE;

  public PrivateMonoClock(Clock backend) {
    this.backend = backend;
  }

  public long micros() {
    lastMicros = Math.max(backend.micros(), lastMicros);
    return lastMicros;
  }
}
