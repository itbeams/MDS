package com.gogglespace.framework.droid;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds entity cache info
 * Created by Shahid Nawaz on 12/17/2015
 * @version 1.0
 * */
public class FieldCacheInfo {

	Map<String, Field> fields; // entity field map by name
	List<FieldInfo> fieldList;
	
	/**
	 * @return the fields
	 */
	Map<String, Field> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	void setFields(List<Field> fields) {
		if (fields == null || fields.isEmpty()) {
			if (this.fields != null) {
				this.fields.clear();
			}
		}
		else {
			if (this.fields != null && !this.fields.isEmpty()) {
				this.fields.clear();
			}
			else {
				this.fields = new HashMap<>(fields.size());
				for (Field f : fields) {
					this.fields.put(f.getName(), f);
				}
			}
		}
	}
	/**
	 * @return the fieldList
	 */
	List<FieldInfo> getFieldList() {
		return fieldList;
	}
	/**
	 * @param fieldList the fieldList to set
	 */
	void setFieldList(List<FieldInfo> fieldList) {
		this.fieldList = fieldList;
	}
	
	
}
