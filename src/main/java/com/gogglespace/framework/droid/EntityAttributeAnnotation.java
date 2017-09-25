package com.gogglespace.framework.droid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation class for entity attribute
 * Created by Shahid Nawaz on 2/4/2015.
 * @version 1.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityAttributeAnnotation {
    String fieldName();
    boolean required() default true; // by default required is set to true
    int maxLength() default 256;
    int minLength() default 1;
    FieldType fieldType();
    boolean primaryKey() default false;
    //String foreignKey(); // foreign key
}