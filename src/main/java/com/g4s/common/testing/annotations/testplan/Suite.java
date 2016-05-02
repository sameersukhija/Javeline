package com.g4s.common.testing.annotations.testplan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.testng.annotations.Test;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Suite {

	public boolean enabled() default true;
	public String[] path() default {};
	
}
