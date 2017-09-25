package com.gogglespace.framework.droid.exceptions;

import java.io.Serializable;

/**
 * A generic runtime exception
 * @author Shahid Nawaz on 03/31/2016
 * @version 1.0
 * */
public class CommonRuntimeException extends RuntimeException  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 * */
	public CommonRuntimeException() {
		super();
	}
	
	/**
	 * @param errMsg - constructor with error message
	 * */
	public CommonRuntimeException(String errMsg) {
		super(errMsg);
	}
	
	public CommonRuntimeException(Exception e) {
		super(e);
		//e.printStackTrace();
	}
	
}
