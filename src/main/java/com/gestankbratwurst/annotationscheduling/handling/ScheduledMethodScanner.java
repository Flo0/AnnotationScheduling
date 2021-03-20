package com.gestankbratwurst.annotationscheduling.handling;

import com.gestankbratwurst.annotationscheduling.annotations.Scheduled;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduledMethodScanner {

  protected ScheduledMethodScanner() {

  }

  protected List<ScheduledMethod> scanForMethods(Object object) {
    List<ScheduledMethod> methods = new ArrayList<>();
    Class<?> clazz = object.getClass();
    for (Method method : clazz.getDeclaredMethods()) {
      Optional<ScheduledMethod> methodOptional = this.wrap(object, method);
      methodOptional.ifPresent(methods::add);
    }
    return methods;
  }

  private Optional<ScheduledMethod> wrap(Object target, Method method) {
    Scheduled scheduledAnnotation = method.getDeclaredAnnotation(Scheduled.class);
    if (scheduledAnnotation == null) {
      return Optional.empty();
    }
    int initialDelay = scheduledAnnotation.initialDelay();
    initialDelay = Math.max(initialDelay, 1);
    int frequency = scheduledAnnotation.frequency();
    frequency = Math.max(frequency, 1);
    return Optional.of(new ScheduledMethod(target, method, initialDelay, frequency));
  }

}
