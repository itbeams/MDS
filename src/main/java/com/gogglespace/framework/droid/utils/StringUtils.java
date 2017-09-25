package com.gogglespace.framework.droid.utils;

import java.util.Locale;

import android.content.Context;

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

    /**
     * This utility method is used to extract value from a json
     * @param strValue - json string
     * @param identifier - attribute / identified to be searched for the value
     * @return String
     * */
    public static String getAttributeValue(String strValue, String identifier) {
        String tmp = strValue;
        int index = tmp.indexOf(identifier);
        tmp = tmp.substring(index+identifier.length());
        int commaIndex = tmp.indexOf(",");
        tmp = tmp.substring(0, commaIndex);

        // trim starting double or single quote
        if(tmp.startsWith("\"") || tmp.startsWith("'")) {
            tmp =  tmp.substring(1);
        }
        // trim ending double or single quote
        if(tmp.endsWith("\"") || tmp.endsWith("'")) {
           tmp = tmp.substring(0,tmp.length()-1);
        }


        return tmp;
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
	 * Get minimum length formatted error message
	 * @param context - caller context
	 * @param minLength - minimum length
	 * @return String - error message
	 * */
	public static String getMinLenthErrMsg(Context context, int minLength) {
		String noneFormattedLabel = "";
		return getFormatted(noneFormattedLabel,  Integer.toString(minLength));
	}

	/**
	 * Get required error message
	 * @param context - caller context
	 * @return String - error message
	 * */
	public static String getRequiredErrMsg(Context context) {
		//SDK Bug in Android error message display - cuts text sometime by 6 ending characters
		final String spaceFiller = "      "; // setError message
		return "";
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
