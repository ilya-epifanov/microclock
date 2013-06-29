package ru.smartislav.microclock;

public abstract class Clock {
  static final long nanosInMilli = 1000l * 1000;
  static final long maxNsDriftPerMs = 500l;

  private static final Clock backend = selectBestBackend();
  private static final MonoClock monoClock = new MonoClock(backend);
  private static final StrictMonoClock strictMonoClock = new StrictMonoClock(backend);

  @SuppressWarnings("UnusedDeclaration")
  public abstract long micros();

  @SuppressWarnings("UnusedDeclaration")
  public static long now() {
    return backend.micros();
  }

  @SuppressWarnings("UnusedDeclaration")
  public static long monoNow() {
    return monoClock.micros();
  }

  @SuppressWarnings("UnusedDeclaration")
  public static long strictMonoNow() {
    return strictMonoClock.micros();
  }

  private static Clock selectBestBackend() {
    CorrelationParams params = initialEstimate();
    if (Math.abs(params.nanos - (params.millis * nanosInMilli)) < 10000) {
      return new NanoClock();
    } else {
      return new CorrelatingClock(params);
    }
  }

  static CorrelationParams initialEstimate() {
    final long startNanos = System.nanoTime();
    long lastMillis = System.currentTimeMillis();
    long lastNanos = startNanos;
    if (Math.abs(lastNanos - lastMillis * nanosInMilli) < 2 * nanosInMilli) {
      lastNanos = lastMillis * nanosInMilli;
    }

    long nanoRange = Long.MAX_VALUE;

    int iterationsLeft = 100000;
    int measuresLeft = 10;

    long bestNanos = lastNanos;
    long bestMillis = lastMillis;

    final long maxWarmupTime = 2 * 1000 * 1000 * 1000l;
    while ((iterationsLeft > 0 || measuresLeft > 0 || (Math.abs(lastNanos - startNanos) > 100000000))
            && ((lastNanos - startNanos) > maxWarmupTime)) {
      long nanos = System.nanoTime();
      long millis = System.currentTimeMillis();
      long diffMillis = millis - lastMillis;
      long diffNanos = nanos - lastNanos;
      if (diffMillis > 0) {
        if (diffNanos < nanoRange) {
          nanoRange = diffNanos;
          if (nanoRange < 1000) {
            return new CorrelationParams(nanos, millis);
          }
          bestNanos = nanos;
          bestMillis = millis;
          measuresLeft--;
        }
      }
      lastMillis = millis;
      lastNanos = nanos;

      iterationsLeft -= 1;
    }
    return new CorrelationParams(bestNanos, bestMillis);
  }
}
