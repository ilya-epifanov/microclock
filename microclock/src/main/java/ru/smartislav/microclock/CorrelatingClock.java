package ru.smartislav.microclock;

import java.util.concurrent.atomic.AtomicReference;

final class CorrelatingClock extends Clock {
  private final AtomicReference<CorrelationParams> params;

  CorrelatingClock(CorrelationParams params) {
    this.params = new AtomicReference<CorrelationParams>(params);
  }

  @Override
  public long micros() {
    CorrelationParams p = params.get();
    long nanos;
    long millis;
    do {
      nanos = System.nanoTime();
      if (nanos - p.nanos < 950000) {
        return micros(p, nanos);
      }
      millis = System.currentTimeMillis();

      if (p.millis != millis) {
        long nextNanos = p.nanos + nanosInMilli * (millis - p.millis);
        long diffNanos = nextNanos - nanos;
        if (diffNanos > -maxNsDriftPerMs * (millis - p.millis)) {
          CorrelationParams newParams = new CorrelationParams(nanos, millis);
          if (params.compareAndSet(p, newParams))
            return micros(newParams, nanos);
          else
            p = params.get();
        } else {
          params.compareAndSet(p, new CorrelationParams(p.nanos + nanosInMilli * (millis - p.millis), millis));
          p = params.get();
        }
      }
    } while (p.millis != millis);
    return micros(p, nanos);
  }

  private static long micros(CorrelationParams params, long currentNanos) {
    return (currentNanos - params.nanos) / 1000 + params.millis * 1000;
  }
}

