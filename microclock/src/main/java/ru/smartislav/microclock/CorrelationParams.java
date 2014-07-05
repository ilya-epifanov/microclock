package ru.smartislav.microclock;

final class CorrelationParams {
  public final long nanos;
  public final long nanoUncertainty;
  public final long millis;
  public final long leapNanos;

  CorrelationParams(long nanos, long millis) {
    this(nanos, 0, millis, 0);
  }

  CorrelationParams(long nanos, long nanoUncertainty, long millis, long leapNanos) {
    this.nanos = nanos;
    this.nanoUncertainty = nanoUncertainty;
    this.millis = millis;
    this.leapNanos = leapNanos;
  }
}
