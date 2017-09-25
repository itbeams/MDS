package com.gogglespace.framework.droid;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.gogglespace.framework.droid.exceptions.CommonRuntimeException;
import com.gogglespace.framework.droid.exceptions.ExceptionUtils;

/**
 * Object utility operations
 * Created by Shahid Nawaz on 2/4/2015.
 *
 * @version 1.0
 */
public class ObjectUtils {

	private static final String BOOLEAN_TRUE = "true";
	private static final String LITERAL_LASTUPDATED = "lastUpdated";
	private static final String LITERAL_ID = "id";

	private ObjectUtils(){
		// restricted outside initialization
	}

    /**
     * This method put the given field value into given object
     *
     * @param obj       - object where value has to be put
     * @param clazz     - class of passed object
     * @param fieldName - {@link String} field(variable) name
     * @param value     - value which has to be set
     */
    public static <T> void findPut(Object obj, Class<T> clazz, String fieldName, Object value) {
        if (clazz.isInstance(obj)) {
            clazz.cast(obj);
        }
        try {
            Field field;
            // handling super class attribute
            if (fieldName.equals(LITERAL_ID) || fieldName.equals(LITERAL_LASTUPDATED)) {
                if (false/*(obj instanceof PatientEntity || obj instanceof TreatmentEntity)&&
                        fieldName.equals(LITERAL_ID) */) {
                    field = clazz.getSuperclass().getSuperclass().getDeclaredField(fieldName);
                }
                else {
                    field = clazz.getSuperclass().getDeclaredField(fieldName);
                }
                field.setAccessible(true);
                field.set(obj, value);

            } else {
                field = clazz.getDeclaredField(fieldName);
                String methodName = "set" + fieldName.substring(0, 1).toUpperCase(Locale.ENGLISH) + fieldName.substring(1);
                Method setter = clazz.getMethod(methodName, field.getType());

                // NOTE: A specialized case on Boolean unfortunately it is not returning as a boolean
                // rather string value - so in order the reflection to work appropriately value (object)
                // needs to be converted to boolean before virtual invoke method execution
                if (field.getType().equals(boolean.class)) {
                    setter.invoke(obj, getBoolean(value));
                }
                else {
                    setter.invoke(obj, value);
                }
            }

        } catch (Exception e) {
            ExceptionUtils.logException(e);
            throw new CommonRuntimeException(e);
        }
    }

    /**
     * This method put the given field value into given object
     *
     * @param obj       - object where value has to be put
     * @param clazz     - class of passed object
     * @param fieldName - {@link String} field(variable) name
     * @param value     - value which has to be set
     * @param fieldsMap    - Field map with fieldName as key
     */
    public static <T> void findPut(Object obj, Class<T> clazz, String fieldName, Object value, Map<String, Field> fieldsMap) {
    	try {
	    	if (fieldsMap == null || fieldsMap.size() == 0 ) {
	    		findPut(obj, clazz, fieldName, value);
	    	}
	    	else {
	            // NOTE: A specialized case on Boolean unfortunately it is not returning as a boolean
	    		Field field = fieldsMap.get(fieldName);
	    		field.setAccessible(true);
	            if (field.getType().equals(boolean.class)) {
	            	field.set(obj, getBoolean(value));
	            }
	            else {
	            	field.set(obj, value);
	            }
	    	}
	    }
    	catch (Exception e) {
            ExceptionUtils.logException(e);
            throw new CommonRuntimeException(e);
        }
    }

    private static boolean getBoolean(Object obj) {
        boolean retVal = false;
        if (obj instanceof String) {
            retVal = obj.toString().equalsIgnoreCase(BOOLEAN_TRUE);
        }

        return retVal;
    }

    public static long[] getArrayFromList(List<Long> list) {
    	long[] array;
    	if (isNullOrEmpty(list)) {
    		return new long[0];
    	}

    	array = new long[list.size()];
    	for(int i = 0; i < list.size(); i++) {
    		array[i] = list.get(i);
    	}
    	return array;
    }

    /**
     * Get canonical name of the class
     * @param clazz - clazz to determine canonical name of e.g com.gogglespace.framework.droid.db.entities.EmployeeEntity
     * @return String - fully qualified class name
     **/
    public static <T> String getClazzCacheKey(T clazz) {
    	return  clazz.getClass().getCanonicalName();
    }

    /**
     * list to array converting
     * @param list - list of objects
     * @param clazz - clazz this list of objects is
     * @return <T> T[] - an array of passed object or null if list is null
     * or empty
     * */
    public static <T> T[] listToArray(List<T> list, Class<T> clazz) {
    	if (isNullOrEmpty(list)) {
    		 /**** Null Checks removed  ****/
    		return null;
    	}
    	else {
	        @SuppressWarnings("unchecked")
			T[] arr = (T[]) Array.newInstance(clazz, list.size());
	        for (int i = 0; i < list.size(); ++i) {
	            arr[i] = list.get(i);
	        }
	        return arr;
    	}
    }

    /**
     * Array to list
     * */
    public static <T> List<T> arrayToList(T[] arrayItems, Class<T> clazz) {
    	@SuppressWarnings("unchecked")
		final T[] data = (T[])new Object[arrayItems==null?0:arrayItems.length];
    	return Arrays.asList(data);
    }

    /**
     * Determines if list is empty or null
     * */
	public static <T> boolean isNullOrEmpty(Collection<T> list) {
	    return list == null || list.isEmpty();
	}
}
