package de.bu.service.identifierservice.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * {@link Qualifier} used to qualify injection points for database access.
 * 
 * @author Philipp Buchholz
 */
@Qualifier
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface Database {

}
