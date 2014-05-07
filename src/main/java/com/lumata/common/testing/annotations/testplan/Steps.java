package com.lumata.common.testing.annotations.testplan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Steps {

	public String[] actions() default {};
	public String[] expected() default {};
	
}
