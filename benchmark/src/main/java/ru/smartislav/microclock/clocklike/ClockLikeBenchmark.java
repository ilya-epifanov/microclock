package ru.smartislav.microclock.clocklike;

import com.google.caliper.Benchmark;
import ru.smartislav.clock.rdtsc.RdTscSource;
import sun.misc.Perf;

@SuppressWarnings("UnusedDeclaration")
public class ClockLikeBenchmark extends Benchmark {
  private final ClockLike rdTscProxy = RdTsc.instance;

  public long timeRdTscViaProvider(int reps) {
    long ticks = 0;
    for (int i = 0; i < reps; i++)
      ticks = rdTscProxy.ticks();
    return ticks;
  }

  public long timeRdTscDirectly(int reps) {
    long ticks = 0;
    for (int i = 0; i < reps; i++)
      ticks += RdTscSource.rdtsc();
    return ticks;
  }

  private final ClockLike sunPerfProxy = SunPerf.instance;

  public long timeSunPerfViaProvider(int reps) {
    long ticks = 0;
    for (int i = 0; i < reps; i++)
      ticks += sunPerfProxy.ticks();
    return ticks;
  }

  private final Perf perf = Perf.getPerf();

  public long timeSunPerfDirectly(int reps) {
    long ticks = 0;
    for (int i = 0; i < reps; i++)
      ticks += perf.highResCounter();
    return ticks;
  }
}
