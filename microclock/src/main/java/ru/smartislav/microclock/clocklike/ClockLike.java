package ru.smartislav.microclock.clocklike;

public abstract class ClockLike {
  abstract long ticks();

  abstract boolean hasDeclaredFrequency();

  abstract long frequency();
}
