package com.gestankbratwurst.annotationscheduling.handling;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ScheduledMethod {

  private final Object target;
  private final Method method;
  private final int initialDelay;
  private final int frequency;
  private int counter = 0;
  private boolean ranOnce = false;

  protected ScheduledMethod(Object target, Method method, int initialDelay, int frequency) {
    this.target = target;
    this.method = method;
    this.initialDelay = initialDelay;
    this.frequency = frequency;
  }

  public void tick() {
    this.counter++;
    if (!this.ranOnce) {
      if (this.counter == this.initialDelay) {
        this.invoke();
        this.ranOnce = true;
      }
      return;
    }
    if (this.counter % this.frequency == 0) {
      this.invoke();
    }
  }

  private void invoke() {
    try {
      this.method.invoke(this.target);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

}
