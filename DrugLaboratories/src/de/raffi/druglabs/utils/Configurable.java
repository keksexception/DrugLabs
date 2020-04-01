package de.raffi.druglabs.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Configurable {
	/**
	 * 
	 * @return {@value Files#TRANSLATION}
	 */
	String value() default Files.TRANSLATION;

}
