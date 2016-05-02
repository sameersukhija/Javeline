package com.g4s.common.testing.annotations.mysql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {

	String table() default "";
	String field() default "";
	String type() default "";
	String mysqlType() default "";
	String javaType() default "";
	String categoryType() default "";
	boolean unsigned() default false;
	boolean isNull() default true;
	boolean isAutoincrement() default false;
	String key()  default "";
	String defaultValue() default "";
	String extra() default "";
	int length() default 0;
	String comment() default "";
	String getMethod() default "";
	String setMethod() default "";
	
}
