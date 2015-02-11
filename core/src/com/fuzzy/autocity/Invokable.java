package com.fuzzy.autocity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={METHOD, TYPE})
public @interface Invokable {
// Marks a class or method so it can be invoked at runtime.
}
