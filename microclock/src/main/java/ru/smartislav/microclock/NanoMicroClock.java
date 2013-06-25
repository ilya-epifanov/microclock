package ru.smartislav.microclock;

public final class NanoMicroClock extends MicroClock {
  @Override
  public long micros() {
    return System.nanoTime() / 1000;
  }
}
