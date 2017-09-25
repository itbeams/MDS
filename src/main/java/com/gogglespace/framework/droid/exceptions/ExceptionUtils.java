package com.gogglespace.framework.droid.exceptions;

import com.gogglespace.framework.droid.utils.StringUtils;

/**
 * Exception / crash persistence utility operations
 * @author Shahid Nawaz on 02/01/2017
 * @version 1.0
 * */
public class ExceptionUtils {

	static final String appException = "Exception";
	static final String appCrash	 = "AppCrash";
	static final int maxCharacters	 = 1000;

	private ExceptionUtils(){
		// instance creation restricted
	}

	public static void logException(Exception e) {
		 logMessage(StringUtils.getString(e), appException);
	}

	public static void logException(String message) {
		 logMessage(message, appException);
	}

	public static void logCrashReport(String crashReport) {
		 logMessage(crashReport, appCrash);
	}

	private static void logMessage(String msg, String msgType) {
		if (StringUtils.hasValue(msg)) {
			// data
		}
	}
}
