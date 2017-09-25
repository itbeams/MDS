package com.gogglespace.framework.droid;

import java.util.HashMap;
import java.util.Map;

/**
 * A db entity structure cache info 
 * Created by Shahid Nawaz on 12/17/2015
 * @version 1.0
 * */
public class EntityInfoCacheHelper {
	
	static Map<String, FieldCacheInfo> cCache;
	
	private EntityInfoCacheHelper() {
		// Restrict outside initialization
	}
	
	static void setCache(String key, FieldCacheInfo fieldCacheInfo ) {
		if (cCache == null) {
			cCache = new HashMap<>();
		}
		
		cCache.put(key, fieldCacheInfo);
	}
	
	static FieldCacheInfo getCacheByKey(String key) {
		if (cCache == null || !cCache.containsKey(key)) {
			return null;
		}
		else {
			return cCache.get(key);
		}
	}
	
	/**
	 * Checks if entity information is in cache
	 * @param entityCacheKey - cache key for entity
	 * @return boolean - true / false
	 * */
	static boolean isInCache(String entityCacheKey) {
		return cCache != null && cCache.containsKey(entityCacheKey);
	}

}
