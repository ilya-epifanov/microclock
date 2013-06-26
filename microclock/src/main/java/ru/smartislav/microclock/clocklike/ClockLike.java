package ru.smartislav.microclock.clocklike;

public abstract class ClockLike {
  public abstract long ticks();

  public abstract boolean hasDeclaredFrequency();

  public abstract long frequency();
}
