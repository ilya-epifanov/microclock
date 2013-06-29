package ru.smartislav.microclock;

public final class NanoClock extends Clock {
  @Override
  public long micros() {
    return System.nanoTime() / 1000;
  }
}
