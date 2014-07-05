package ru.smartislav.microclock;

public final class StableCorrelator {
  public CorrelationParams adjust(CorrelationParams params, long nanos, long millis) {
    long milliDiff = millis - params.millis;
    if (milliDiff == 0)
      return params;

    long milliDiffInNanos = milliDiff * 1000000;
    long expectedMaxNanoDiff = milliDiffInNanos + 999999;
    long expectedMinNanoDiff = milliDiffInNanos - 999999;
    long nanoDiff = nanos - params.nanos;
    if (nanoDiff > expectedMaxNanoDiff) {
      return params;
    } else if (nanoDiff < expectedMinNanoDiff) {
      return params;
    } else {
      return params;
    }
  }
}
