package ru.smartislav.microclock;

final class CorrelationParams {
  public final long nanos;
  public final long millis;

  CorrelationParams(long nanos, long millis) {
    this.nanos = nanos;
    this.millis = millis;
  }
}
