package com.gogglespace.framework.droid.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * A utility class for common utility operations
 * Created by Shahid Nawaz on 2/5/2015.
 * @version 1.0
 */
public class CommonUtils {

	private CommonUtils(){
		// Outside object initialize restriction
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
     * Encode parameters
     * @param param - parameter to be encoded i.e. removed spaces etc
     * @return String - encoded parameter
     * */
    public static String encodeParam(String param) throws UnsupportedEncodingException {
    	if (StringUtils.hasValue(param)) {
    		return URLEncoder.encode(param, "UTF-8");
    	}
    	else {
    		return StringUtils.getString(param);
    	}
    }

}
