package ru.smartislav.microclock;

import com.google.caliper.Benchmark;

@SuppressWarnings("UnusedDeclaration")
public class ClockBenchmark extends Benchmark {
  public long timeNanoTime(int reps) {
    long nanos = 0;
    for (int i = 0; i < reps; i++)
      nanos += System.nanoTime();
    return nanos;
  }

  private final NanoClock nanoMicroClock = new NanoClock();

  public long timeNanoClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += nanoMicroClock.micros();
    return micros;
  }

  private final CorrelatingClock corrClock = new CorrelatingClock(Clock.initialEstimate());

  public long timeCorrelatingClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += corrClock.micros();
    return micros;
  }

  private final MonoClock weakTSCorrClock = new MonoClock(corrClock);

  public long timeSharedMonoCorrClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += weakTSCorrClock.micros();
    return micros;
  }

  private final StrictMonoClock strictTSCorrClock = new StrictMonoClock(corrClock);

  public long timeSharedStrictMonoCorrClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += strictTSCorrClock.micros();
    return micros;
  }

  private final MonoClock weakTSNanoClock = new MonoClock(nanoMicroClock);

  public long timeSharedMonoNanoClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += weakTSNanoClock.micros();
    return micros;
  }

  private final StrictMonoClock strictTSNanoClock = new StrictMonoClock(nanoMicroClock);

  public long timeSharedStrictMonoNanoClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += strictTSNanoClock.micros();
    return micros;
  }

  private final PrivateMonoClock weakCorrClock = new PrivateMonoClock(corrClock);

  public long timePrivateMonoCorrClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += weakCorrClock.micros();
    return micros;
  }

  private final PrivateStrictMonoClock strictCorrClock = new PrivateStrictMonoClock(corrClock);

  public long timePrivateStrictMonoCorrClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += strictCorrClock.micros();
    return micros;
  }

  private final PrivateMonoClock weakNanoClock = new PrivateMonoClock(nanoMicroClock);

  public long timePrivateMonoNanoClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += weakNanoClock.micros();
    return micros;
  }

  private final PrivateStrictMonoClock strictNanoClock = new PrivateStrictMonoClock(nanoMicroClock);

  public long timePrivateStrictMonoNanoClock(int reps) {
    long micros = 0;
    for (int i = 0; i < reps; i++)
      micros += strictNanoClock.micros();
    return micros;
  }
}
