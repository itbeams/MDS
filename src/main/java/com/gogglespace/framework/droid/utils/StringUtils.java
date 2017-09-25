package com.gogglespace.framework.droid.utils;

import java.util.Locale;

/**
 * A common utility operations 
 * Created by Shahid Nawaz on 2/3/2015.
 * @version 1.0
 */
public class StringUtils {

    private static final String EMPTY_STRING = "";

    private StringUtils(){
    	// Restrict outside initialization
    }

    /**
     * Return empty string
     * */
    public static String emptyString(){
    	return EMPTY_STRING;
    }

    /**
     * Get String value, trim leading spaces, return empty string if str is null or Null
     * string or null reference
     * @param str
     * @return String
     * */
    public static String getString(String str) {
    	final String nullString = "null";
        String retVal = "";
        if (str != null && str.length() > 0 && !str.toLowerCase(Locale.ENGLISH).trim().equals(nullString)) {
            retVal = str.trim();
        }
        return retVal;
    }

    /**
     * Get String value from object, trim leading spaces, return empty string if str is null or Null
     * string or null reference
     *
     * @param objStr
     *
     * @return String
     * */
    public static String getString(Object objStr) {
        String retVal = "";
        if (objStr != null ){
            retVal = getString(objStr.toString());
        }
        return retVal;
    }
    /**
     * Takes String object and returns long value, -1 if object is null, or object has null as a
     * value.
     * @param obj
     * @see StringUtils -> getString(obj)
     * @return long - long value
     * */
    public static long getLong(Object obj) {
        long retVal = -1;
        String longStr = StringUtils.getString(obj);
        if (longStr.length() > 0 ) {
            retVal = Long.parseLong(longStr);
        }
        return retVal;
    }
    
    /**
     * Check if passed String has some value (note null reference or null String value will be discarded
     * for value check)
     *
     * @param str - String to check value of
     *
     * @return boolean - true if passed string has value false otherwise
     * */
    public static boolean hasValue(String str) {
        String val = getString(str);
        return val.length() > 0;
    }

    /**
     * Check if passed object has some value
     * @param obj - String to check value of
     * @return boolean - true if passed object has value false otherwise
     * */
    public static boolean hasValue(Object obj) {
        String val = getString(obj);
        return val.length() > 0;
    }

    /**
     * Get string length
     * @param str - String to check length value of
     * @return int - string length
     * */
    public static int getLength(String str) {
       return getString(str).length();
    }

	public static int getInt(String string) {
		return Integer.parseInt(string);
	}

	public static float getFloat(String string) {
		float retVal = 0.0f;
		if (hasValue(string)) {
			String tmpString = getString(string); // trim it
			retVal= Float.parseFloat(tmpString);
		}
		return retVal;
	}

	public static long getLong(String string) {
		long retVal = 0;
		if (hasValue(string)) {
			String tmpString = getString(string); // trim it
			retVal= Long.parseLong(tmpString);
		}
		return retVal;
	}

	public static Long getLongObj(String string) {
		long retVal = 0;
		if (hasValue(string)) {
			String tmpString = getString(string); // trim it
			retVal= Long.parseLong(tmpString);
		}
		return retVal;
	}

	public static boolean isNumeric(String arg) {
		if (!StringUtils.hasValue(arg)) {
			return false;
		}
		else {
			return arg.matches("[+-]?\\d*(\\.\\d+)?");
		}
	}

	public static boolean isNumeric(Object arg) {
		return isNumeric(getString(arg));
	}

	/**
	 * Get boolean value from string true / false
	 * @param booleanStr - boolean string i.e. true / false
	 * @return boolean - true / false
	 * */
	public static boolean getBoolean(String booleanStr) {
		final String trueStr = "true";
		return getString(booleanStr).equalsIgnoreCase(trueStr);
	}

	/**
	 * Get formatted value
	 * @param strToFormat - message to format
	 * @param formatValue - message place holder value
	 * @return String - formatted value
	 * */
	public static String getFormatted(String msgToFormat, String formatValue) {
		return String.format(msgToFormat, formatValue);
	}

	/**
	 * Return sub string
	 * @param str
	 * @param maxCharacters
	 * */
	public static String getString(String str, int maxCharacters) {
		String strData = getString(str);
		return strData.length()>maxCharacters?strData.substring(0, maxCharacters):strData;
	}
}
