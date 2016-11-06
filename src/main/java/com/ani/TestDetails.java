package com.ani;

import java.lang.annotation.Retention;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface TestDetails {

	/** The unique identifier for the test */
	String testUID();

	/** Tags applied for the test */
	String[] tags() default {};

}
