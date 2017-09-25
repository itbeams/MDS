package com.gogglespace.framework.droid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation definition for entities
 * @author Shahid Nawaz on 2/4/2015.
 * updated on 12/17/2015, 2/24/2016
 * - Added support for caching entity fields info
 * - Added cipherEntity for pointing db to secure see {@link SecureDbRepository} 
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityAnnotation {

	/**
	 * Actual table name mapped to entity
	 * */
    String tableName();

    /**
     * This attribute is just entity comment - to help understand later
     * */
    String comments();

    /**
     * Used to cache entity information, including
     * attributes etc. that will be used for db
     * operations
     * */
    boolean cacheEntityInfo() default false;

    /**
     * Used to create schema for Cipher db
     * @see implementing entity
     * */
    boolean cipherEntity() default false;
}