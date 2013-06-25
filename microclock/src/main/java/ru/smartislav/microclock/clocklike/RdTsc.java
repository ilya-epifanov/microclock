package ru.smartislav.microclock.clocklike;

import java.lang.reflect.Method;

final class RdTsc {
  static final ClockLike instance = obtainInstance();

  private static ClockLike obtainInstance() {
    try {
      Class<?> klass = Class.forName("ru.smartislav.spectator2.clock.clocklike.RdTscProvider");
      Method getMethod = klass.getMethod("obtainInstance");
      return (ClockLike)getMethod.invoke(null);
    } catch (Throwable ignored) {
    }

    return null;
  }
}
