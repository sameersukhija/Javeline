package com.lumata.e4o.testing.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TCSSHService {

	public String ssh_server() default "";
	public String ssh_user() default "";
	
}
