package com.gogglespace.framework.droid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gogglespace.framework.droid.exceptions.CommonRuntimeException;
import com.gogglespace.framework.droid.exceptions.ExceptionUtils;

/**
 * An annotation helper utility class used for inspecting annotations of given object
 * Created by Shahid Nawaz on 2/4/2015.
 * @version 1.0
 */
public class AnnotationHelper {

    public enum FIELD_INFO {
        DEFINITION_ONLY, INCLUDE_VALUES
    }
    
    private AnnotationHelper(){
    	// Outside initialization restriction
    }
    
    private static List<Field> getInheritedPrivateFields(Class<?> type) {
        List<Field> result = new ArrayList<>();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            for (Field field : i.getDeclaredFields()) {
                if (!field.isSynthetic()) {
                    result.add(field);
                }
            }
            i = i.getSuperclass();
        }

        return result;
    }
    
    //-------------------------------------------------------------------------
    /**
     * Method to process all the annotations
     * @param clazz    The name of the object where
     *               annotations are to be identified and
     *               processed
     * @param fieldInfoEnum - field info @see {@link FIELD_INFO}
     */
    @SuppressWarnings("rawtypes")
	static <T> List<FieldInfo> getFieldsInfo(T clazz, FIELD_INFO fieldInfoEnum) {
    	String cacheKey = ObjectUtils.getClazzCacheKey(clazz);
    	List<FieldInfo> fieldDefList;
    	FieldCacheInfo objCache = EntityInfoCacheHelper.getCacheByKey(cacheKey);
    	if (objCache != null) {
    		fieldDefList = objCache.getFieldList();
    		List<FieldInfo> clonedFieldList = getClonedFieldList(fieldDefList);
    		Map<String, Field> fieldsMap = objCache.getFields();
    		Field field;
    		if (fieldInfoEnum == FIELD_INFO.INCLUDE_VALUES) {
    			String fieldName;
				for (FieldInfo cf : clonedFieldList) {
					fieldName = cf.getFieldName();
					if (fieldsMap.containsKey(fieldName)) {
						field = fieldsMap.get(fieldName);
						field.setAccessible(true);
	                    try {
	                        Object val = field.get(clazz);
	                        if (val != null) {
	                        	cf.setData(val);
	                        }
	                    } catch (Exception e) {
	                    	// No logging required
	                    	throw new CommonRuntimeException(e);
	                    }
					}
				}
    		}
    		return clonedFieldList;
    	}
        fieldDefList = new ArrayList<>();
        String tableName = null;
        boolean cipherEntity = false;
        boolean neededEntityInfoCache = false;
        FieldInfo fieldInfo;
        List<Field> fields = null;
        List<FieldInfo> clonnedFieldInfoList = null;
        FieldInfo clonnedFieldInfo;
        try {
            Class cl = clazz.getClass();

            // Checking for table name & cipher
            Annotation[] annotations = cl.getDeclaredAnnotations();
            if (annotations != null && annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == EntityAnnotation.class) {
                        EntityAnnotation entityAnnotation = (EntityAnnotation) annotation;
                        tableName = entityAnnotation.tableName();
                        neededEntityInfoCache = entityAnnotation.cacheEntityInfo();
                        cipherEntity = entityAnnotation.cipherEntity();
                    } else {
                       //  Annotation found but not of EntityAnnotation Type 
                    }
                }
            }
            else {
                //============================== NO ANNOTATION AVAILABLE AT CLASS LEVEL
            }

            // Checking all the fields for annotations
           fields = getInheritedPrivateFields(cl);
            for(Field f : fields) {
                // Since we are Validating fields, there may be many
                // NullPointer and similar exceptions thrown,
                // so we need  to catch them
                try {
                    // Processing all the annotations on a single field
                    for(Annotation a : f.getAnnotations()) {
                        // Checking for attribute annotations
                        if(a.annotationType() == EntityAttributeAnnotation.class) {
                            EntityAttributeAnnotation entityAttributeAnnotation = (EntityAttributeAnnotation) a;
                            fieldInfo = new FieldInfo(tableName);
                            
                            fieldInfo.setColumnName(entityAttributeAnnotation.fieldName());
                            fieldInfo.setFieldName(f.getName());
                            fieldInfo.setRequired(entityAttributeAnnotation.required());
                            fieldInfo.setMaxLength(entityAttributeAnnotation.maxLength());
                            fieldInfo.setMinLength(entityAttributeAnnotation.minLength());
                            fieldInfo.setFieldType(entityAttributeAnnotation.fieldType());
                            fieldInfo.setPrimaryKey(entityAttributeAnnotation.primaryKey());
                            
                            // cipher required
                            fieldInfo.setCipher(cipherEntity);
                            
                            if (neededEntityInfoCache) {
                            	if (clonnedFieldInfoList == null) {
                            		clonnedFieldInfoList = new ArrayList<>();
                            	}
                        		clonnedFieldInfo = getCloned(fieldInfo);
                        		clonnedFieldInfoList.add(clonnedFieldInfo);
                            }

                            // Setting the field to be accessible from our class
                            // is it is a private member of the class under processing
                            // (which its most likely going to be)
                            // The setAccessible method will not work if you have
                            // Java SecurityManager configured and active.
                            if (fieldInfoEnum == FIELD_INFO.INCLUDE_VALUES) {
                                f.setAccessible(true);

                                Object val = f.get(clazz);
                                if (val != null) {
                                    fieldInfo.setData(val);
                                }
                            }
                            
                            fieldDefList.add(fieldInfo);
                        }
                    }
                } catch(Exception e) {
                   // No exception logging required for this
                	throw new CommonRuntimeException(e);
                }
            }
        } catch(Exception e) {
            // No exception logging required for this
         	throw new CommonRuntimeException(e);
        }
        
        if (neededEntityInfoCache){
        	FieldCacheInfo fCacheInfo = new FieldCacheInfo();
        	fCacheInfo.setFieldList(clonnedFieldInfoList);
        	fCacheInfo.setFields(fields);
        	EntityInfoCacheHelper.setCache(cacheKey,fCacheInfo);
        }
        
        return fieldDefList;
    }
    

    /**
     * Get list of cloned FieldInfo 
     * @param fieldList - List of FieldInfo
     * @return List<FieldInfo> - Cloned list of FieldInfo
     * @see {@link FieldInfo}, {@link Cloneable}
     * */
	private static List<FieldInfo> getClonedFieldList(List<FieldInfo> fieldList)   {
		List<FieldInfo> retList = new ArrayList<>(fieldList.size());
		for (FieldInfo ce : fieldList) {
		 FieldInfo fi = getCloned(ce);
		 retList.add(fi);
		}
		return retList;
	}
	
	/**
	 * Clones FieldInfo - shallow clone is required for FPF
	 * @param fieldInfo - FieldInfo
	 * @return FieldInfo
	 * @see {@link FieldInfo}
	 * */
	private static FieldInfo getCloned(FieldInfo fieldInfo) {
		try { 
			 return (FieldInfo)fieldInfo.clone();
		} catch (CloneNotSupportedException cnfse) {
			ExceptionUtils.logException("Clone Exception:"+cnfse);
			// No exception record required
			throw new CommonRuntimeException(cnfse);
		}
	}

}
