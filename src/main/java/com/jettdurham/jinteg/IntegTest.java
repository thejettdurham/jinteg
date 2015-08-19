package com.jettdurham.jinteg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Annotates a method to be run as a unit test.
 * Currently not used, but saved for future use.
 * @author jettdurham
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IntegTest {

}
