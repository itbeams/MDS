package com.gogglespace.framework.droid.exceptions;

import java.io.Serializable;

/**
 * Generic exception
 * Created by Shahid Nawaz on 04/04/2016
 * @version 1.0
 * */
public class CommonException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 * */
	public CommonException() {
		super();
	}
	
	/**
	 * @param e - constructor with error message
	 * */
	public CommonException(NoClassDefFoundError e) {
		super(e);
	}
	
	public CommonException(Exception e) {
		super(e);
	}
	
}
