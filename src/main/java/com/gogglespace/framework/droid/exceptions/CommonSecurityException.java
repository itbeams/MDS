package com.gogglespace.framework.droid.exceptions;

import java.io.Serializable;

/**
 * Generic security exception
 * Created by Shahid Nawaz on 04/14/2016
 * @version 1.0
 * */
public class CommonSecurityException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 * */
	public CommonSecurityException() {
		super();
	}
	
	/**
	 * @param errMsg - constructor with error message
	 * */
	public CommonSecurityException(String errMsg) {
		super(errMsg);
	}
	
	public CommonSecurityException(Exception e) {
		super(e);
	}
	
}
