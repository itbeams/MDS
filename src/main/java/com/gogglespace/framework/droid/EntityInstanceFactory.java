package com.gogglespace.framework.droid;

import com.gogglespace.framework.droid.exceptions.CommonRuntimeException;
import com.gogglespace.framework.droid.exceptions.ExceptionUtils;

/**
 * Instance factory for entities
 * Created by Shahid Nawaz on 2/4/2015.
 * @version 1.0
 */
class EntityInstanceFactory {
	
    /**
     * Creates new entity instance
     * */
    @SuppressWarnings("unchecked")
	public <T> Object getInstance(T type) {
        Class<T> mClass = (Class<T>) type.getClass();
        try{
            return mClass.newInstance();
        }catch(Exception e){
        	ExceptionUtils.logException(e);
            throw new CommonRuntimeException(e);
        }
    }
}
