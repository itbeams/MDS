package com.gogglespace.framework.droid.exceptions;

import java.io.Serializable;

/**
 * An exception class for session timeout from server
 * Created by Shahid Nawaz on 06/24/2015
 * @version 1.0
 * */
public class SessionTimeoutException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String errorMsg;

	/**
	 * Default constructor
	 * */
	public SessionTimeoutException() {
		super();
		resetExecptionMsgInfo();
	}
	
	/**
	 * @param errMsg - constructor with error message
	 * */
	public SessionTimeoutException(String errMsg) {
		super(errMsg);
		resetExecptionMsgInfo();
		this.errorMsg = errMsg;
	}
	
	/**
	 * @param errMsg - constructor with error message
	 * */
	public SessionTimeoutException(int errorCode, String errMsg) {
		super(errMsg);
		resetExecptionMsgInfo();
		this.errorMsg = errMsg;
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
	private void resetExecptionMsgInfo() {
		errorCode = -1;
		errorMsg = "";
	}
}
