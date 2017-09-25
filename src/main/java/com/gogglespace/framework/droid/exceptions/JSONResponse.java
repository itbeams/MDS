package com.gogglespace.framework.droid.exceptions;

/**
 * Defines error code for invalid (none JSON) response form server
 * Created by Shahid Nawaz on 08/14/2015
 * @version 1.0
 * */
public class JSONResponse {
	
	// Invalid json - expected json but found none json response
	public static final int BAD_JSON_RESPONSE_ERROR_CODE 	= 1000;
	public static final String INVALID_RESPONSE_RETURN 		= "Invalid response returned... ";
	public static final String SUCCESSFUL_RESPONSE 			= "Successful";
	public static final String FAILED_RESPONSE 				= "Failed";
	
	
	private JSONResponse(){
		// restrict outside initialization
	}

}
