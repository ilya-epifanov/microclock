package ru.smartislav.microclock.clocklike;

import sun.misc.Perf;

@SuppressWarnings("UnusedDeclaration")
final class SunPerfProvider {
  static ClockLike obtainInstance() {
    final Perf perf = Perf.getPerf();

    return new ClockLike() {
      @Override
      public long ticks() {
        return perf.highResCounter();
      }

      @Override
      public boolean hasDeclaredFrequency() {
        return true;
      }

      @Override
      public long frequency() {
        return perf.highResFrequency();
      }
    };
  }
}
