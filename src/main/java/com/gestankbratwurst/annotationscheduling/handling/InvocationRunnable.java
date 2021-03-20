package com.gestankbratwurst.annotationscheduling.handling;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;

public class InvocationRunnable implements Runnable {

  private final ArrayList<ScheduledMethod> invocationMethods = new ArrayList<>();

  protected InvocationRunnable() {

  }

  protected void addMethod(ScheduledMethod method) {
    Preconditions.checkArgument(method != null);
    this.invocationMethods.add(method);
  }

  protected void addAll(Collection<ScheduledMethod> methods) {
    methods.forEach(this::addMethod);
  }

  @Override
  public void run() {
    this.invocationMethods.forEach(ScheduledMethod::tick);
  }

}
