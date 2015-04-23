package com.lumata.common.testing.network;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Mail {

	public String protocol() default "";
	
	public String fromRecipient() default "";
	
	public String toRecipient() default "";
	
	public String[] toRecipients() default {};
	
	public String host() default "";

	public int port() default 0;
	
	public boolean starttlsEnabled() default false;
	
	public boolean authorizationRequired() default false;
	
	public String user() default "";
	
	public String password() default "";
	
}
