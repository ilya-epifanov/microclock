package ru.smartislav.microclock.clocklike;

import ru.smartislav.clock.rdtsc.RdTscSource;

@SuppressWarnings("UnusedDeclaration")
final class RdTscProvider {
  static ClockLike obtainInstance() {
    return new ClockLike() {
      @Override
      public long ticks() {
        return RdTscSource.rdtsc();
      }

      @Override
      public boolean hasDeclaredFrequency() {
        return false;
      }

      @Override
      public long frequency() {
        return 0;
      }
    };
  }
}
