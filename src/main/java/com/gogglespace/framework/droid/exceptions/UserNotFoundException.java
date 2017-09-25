package com.gogglespace.framework.droid.exceptions;

import java.io.Serializable;

/**
 * An exception class when user is not found in the system
 * Created by Shahid Nawaz on 05/07/2015
 * @version 1.0
 * */
public class UserNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * */
	public UserNotFoundException() {
		super();
	}
	
	/**
	 * @param errMsg - constructor with error message
	 * */
	public UserNotFoundException(String errMsg) {
		super(errMsg);
	}
}
