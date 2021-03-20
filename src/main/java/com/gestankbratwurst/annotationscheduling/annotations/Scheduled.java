package com.gestankbratwurst.annotationscheduling.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of AnnotationScheduling and was created at the 20.03.2021
 *
 * AnnotationScheduling can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Scheduled {

  int initialDelay() default 1;
  int frequency() default 1;

}
