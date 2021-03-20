package com.gestankbratwurst.annotationscheduling.handling;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;


/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of AnnotationScheduling and was created at the 20.03.2021
 *
 * AnnotationScheduling can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
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
