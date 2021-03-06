package com.dependencyInjection.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(CONSTRUCTOR)
@Retention(RUNTIME)
@Documented
public @interface Inject {

}
