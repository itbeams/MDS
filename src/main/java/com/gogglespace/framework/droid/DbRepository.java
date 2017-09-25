package com.gogglespace.framework.droid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gogglespace.framework.droid.db.entities.BaseEntity;
import com.gogglespace.framework.droid.utils.StringUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *  Droid Persistence Framework DB repository supports 
 *  multi threading as well
 * <br />It is a generic repository class for db adapters to make all operations
 * @author Shahid Nawaz on 2/4/2015.
 * @version 1.0
 */
public class DbRepository<T> extends BaseMTDBRepository<T> {
    public DbRepository(Context context, T o) {
        super(context, o);
    }

    /**
     * Get a list of entity objects of specified return type matching given criteria
     * @param criteria - criteria to restrict fetch list
     * @see Criteria
     * @return List<T> - where T is of type entity caller is assigned to
     * */
    @SuppressWarnings({ "hiding", "unchecked" })
    @Override
	public <T> List<T> getAll(Criteria criteria){
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        EntityInstanceFactory instanceFactory = new EntityInstanceFactory();
        String whereClause = helper.getWhereClause(criteria);
        String[] whereArgs = helper.getWhereArgs(criteria);
        String orderByClause = helper.getOrderByClause(criteria);
        SQLiteDatabase db = this.getDb();
        Cursor cursor = db.query(entityDetails.tableName, entityDetails.selectedColumnList, whereClause, whereArgs, null, null, orderByClause);
        
        // now fetch items and iterate over
        List<T> entities = new ArrayList<>();
        
        Map<String, Field> entityFieldCache = null;
        if (cursor != null) {
            cursor.moveToFirst();
            // check to see if specified entity information is cached
            if(cursor.isFirst()){
            	String entityCacheKey = ObjectUtils.getClazzCacheKey(type);
                if( EntityInfoCacheHelper.isInCache(entityCacheKey)){
                	entityFieldCache = EntityInfoCacheHelper.getCacheByKey(entityCacheKey).getFields();
                }
            }
            while(!cursor.isAfterLast()){
                T entity = (T) instanceFactory.getInstance(type);
                for(FieldInfo f : entityDetails.fields) {
                    Object objVal;
                    FieldType fieldType = f.getFieldType();

                    String fType = fieldType.name();
                    switch (fType) {
                        case DataType.TYPE_LONG: 
                            objVal = cursor.getLong(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        case DataType.TYPE_STRING: 
                            objVal = cursor.getString(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        case DataType.TYPE_BOOLEAN: 
                            objVal = cursor.getString(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        case DataType.TYPE_INT: 
                            objVal = cursor.getInt(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        default: 
                            objVal = cursor.getString(cursor.getColumnIndex(f.getColumnName()));
                            break;
                    }

                    ObjectUtils.findPut(entity, entity.getClass(), f.getFieldName(), objVal, entityFieldCache);
                }
                entities.add(entity);
                cursor.moveToNext();
            }
            cursor.close();
            this.close();
        }
        return entities;
    }

    /**
     * Retrieve an entity by Id (pk)
     * @param id - pk
     * @return T - entity of caller type
     * */
    @SuppressWarnings({ "hiding", "unchecked" })
    @Override
	public <T> T get(long id) {
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        SQLiteDatabase db = this.getDb();
        
        String[] selectionArgs = {StringUtils.getString(id)};
        Cursor cursor = db.query(entityDetails.tableName, entityDetails.selectedColumnList, entityDetails.getIDField() + " = ?",
        		selectionArgs, null, null, null);
        EntityInstanceFactory instanceFactory = new EntityInstanceFactory();
        Map<String, Field> entityFieldCache = null;
        // now fetch items and iterate over
        T entity = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if(cursor.isFirst()){
                entity = (T) instanceFactory.getInstance(type);
                String entityCacheKey = ObjectUtils.getClazzCacheKey(entity);
                if( EntityInfoCacheHelper.isInCache(entityCacheKey)){
                	entityFieldCache = EntityInfoCacheHelper.getCacheByKey(entityCacheKey).getFields();
                }
                for(FieldInfo f : entityDetails.fields) {
                    Object objVal;
                    FieldType fieldType = f.getFieldType();
                    String fType = fieldType.name();
                    switch (fType) {
                        case DataType.TYPE_LONG: 
                            objVal = cursor.getLong(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        case DataType.TYPE_STRING: 
                            objVal = cursor.getString(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        case DataType.TYPE_BOOLEAN: 
                            objVal = cursor.getString(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        case DataType.TYPE_INT: 
                            objVal = cursor.getInt(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        default: 
                            objVal = cursor.getString(cursor.getColumnIndex(f.getColumnName()));
                            break;
                    }
                    ObjectUtils.findPut(entity, entity.getClass(), f.getFieldName(), objVal, entityFieldCache);
                }
                cursor.moveToNext();
            }
            cursor.close();
            this.close();
        }


        return entity;
    }

    /**
     * Create / update an entity object
     *
     * @param obj - an entity to be saved / update in database
     *
     * @return object of type T 
     * */
    @SuppressWarnings({ "hiding" })
	public <T> T saveOrUpdateOld(T obj) {
        List<FieldInfo> fieldList = AnnotationHelper.getFieldsInfo(obj,
                AnnotationHelper.FIELD_INFO.INCLUDE_VALUES);
        ContentValues cv = new ContentValues();
        String tableName = null;
        String idField = "";
        boolean update = true;
        long rowId = 0;
        for (FieldInfo f : fieldList) {
            if (tableName == null) {
                tableName = f.getTableName();
            }
            // skip because primary key is auto generated number
            if (f.isPrimaryKey() ) {
            	if (StringUtils.getLong(f.getData()) <= 0) {
	                update = false;
	                continue;
            	}
            	else {
                    idField = f.getColumnName();
                    rowId = StringUtils.getLong(f.getData());
            	}
            }
            cv.put(f.getColumnName(), StringUtils.getString(f.getData()));
        }
        
        if (!update) {
            long id = getDb().insert(tableName, null, cv);
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setId(id);
        } else {
            getDb().update(tableName, cv,  QueryUtils.getWhereClauseIdField(idField), QueryUtils.getSelectionArg(rowId));
        }
        close();
        return obj;
    }
    
    /**
     * Create / update an entity object
     * @param obj - an entity to be saved / update in database
     * @return object of type T 
     * */
    @SuppressWarnings({ "hiding" })
    @Override
	public <T> T saveOrUpdate(T obj) {
        List<FieldInfo> fieldList = AnnotationHelper.getFieldsInfo(obj,
                AnnotationHelper.FIELD_INFO.INCLUDE_VALUES);
        ContentValues cv = new ContentValues();
        String tableName = null;
        String idField = "";
        boolean update = true;
        long rowId = 0;
        for (FieldInfo f : fieldList) {
            if (tableName == null) {
                tableName = f.getTableName();
            }
            // skip because primary key is auto generated number
            if (f.isPrimaryKey() ) {
            	if (StringUtils.getLong(f.getData()) <= 0) {
	                update = false;
	                continue;
            	}
            	else {
                    idField = f.getColumnName();
                    rowId = StringUtils.getLong(f.getData());
            	}
            }
            cv.put(f.getColumnName(), StringUtils.getString(f.getData()));
        }
        
        if (!update) {
            long id = getDb().insert(tableName, null, cv);
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setId(id);
        } else {
			getDb().update(tableName, cv,  QueryUtils.getWhereClauseIdField(idField), QueryUtils.getSelectionArg(rowId));
        }
        close();
        return obj;
    }

    /**
     * Save or update entities
     * @param objList - list of entities to be inserted / updated
     * @return List<T> - list of entities after updating 
     * */
    @SuppressWarnings({ "hiding"})
    @Override
	public <T> List<T> saveOrUpdate(List<T> objList) {
		List<FieldInfo> fieldList;
        ContentValues cv;
        String tableName;
        String idField;
        boolean update;
        long rowId;

		if (!ObjectUtils.isNullOrEmpty(objList)) {
			SQLiteDatabase db = getDb();
				for (T obj : objList) {
	    	        tableName = null;
	    	        idField = "";
	    	        update = true;
	    	        rowId = 0;
	        		cv = new ContentValues();
					fieldList = AnnotationHelper.getFieldsInfo(obj, AnnotationHelper.FIELD_INFO.INCLUDE_VALUES);
					for (FieldInfo f : fieldList) {
						if (tableName == null) {
							tableName = f.getTableName();
						}
						// skip because primary key is auto generated number
			            if (f.isPrimaryKey() ) {
			            	if (StringUtils.getLong(f.getData()) <= 0) {
				                update = false;
				                continue;
			            	}
			            	else {
			                    idField = f.getColumnName();
			                    rowId = StringUtils.getLong(f.getData());
			            	}
			            }
						cv.put(f.getColumnName(), StringUtils.getString(f.getData()));
					}

					if (!update) {
						long id = db.insert(tableName, null, cv);
						BaseEntity baseEntity = (BaseEntity) obj;
						baseEntity.setId(id);
					} else {
						db.update(tableName, cv, QueryUtils.getWhereClauseIdField(idField), 
								QueryUtils.getSelectionArg(rowId));
					}
				}
				close();
		}
        
        return objList;
    }
    
    /**
     * Delete by provided criteria
     * @param criteria - delete entity/entities based on criteria
     * @return INT - returns -1 for deletion failed and positive value 1 otherwise
     * */
    @Override
	public int delete(Criteria criteria){
        int status = -1;
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        String whereClause = helper.getWhereClause(criteria);
        String[] whereArgs = helper.getWhereArgs(criteria);
        SQLiteDatabase db = getDb();
        boolean execStatus = db.delete(entityDetails.getTableName(), whereClause , whereArgs) > 0;
        if (execStatus) {
            status = 1;
        }
        close();
        return status;
    }
    /**
     * Delete passed entity
     *
     * @param obj - an entity to be deleted
     * @return int - returns -1 for deletion failed and positive value 1 otherwise
     * */
    @SuppressWarnings({"hiding" })
    @Override
	public <T> int delete(T obj){
        int status = -1;
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        BaseEntity baseEntity = (BaseEntity) obj;
        
        SQLiteDatabase db = getDb();
        boolean execStatus = db.delete(entityDetails.getTableName(), 
        		QueryUtils.getWhereClauseIdField(entityDetails.getIDField()), QueryUtils.getSelectionArg(baseEntity.getId())) > 0;
        if (execStatus) {
            status = 1;
        }
        close();
        return status;
    }

   
    /**
     * Based on criteria determines if there are records available or not
     * @param criteria - criteria 
     * @return boolean - true / false
     * */
    @Override
    public boolean hasRecords(Criteria criteria){
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        final String whereClause = helper.getWhereClause(criteria);
        final String[] whereArgs = helper.getWhereArgs(criteria);
        final String[] selectedColumnList = new String[]{"count(*)"};
        SQLiteDatabase db = getDb();
        Cursor recordCountCursor = db.query(entityDetails.tableName, selectedColumnList, whereClause, whereArgs, null, null, null);
        recordCountCursor.moveToFirst();
        int recordCount = recordCountCursor.getInt(0);
        recordCountCursor.close();
        close();
        return recordCount > 0;
    }

    /**
     * Reset sequence of an entity as provided
     * @param obj - an entity to be saved / update in database
     * */
    @Override
	public void resetSequence(T obj, long sequence) {
		DbRepositoryHelper helper = new DbRepositoryHelper();
		DbRepositoryHelper.EntityDetails entityDetails = helper.getEntityDetailsOnly(type);
		final String nameColumn = "NAME";
		final String seqColumn  = "SEQ";
		final String whereClause = nameColumn+" = ?";
		final String[] whereArgs = new String[] { entityDetails.tableName };
		final String seqTableName = "SQLITE_SEQUENCE";
		boolean hasSequence = existsSequence(seqTableName, whereClause, whereArgs);
		ContentValues cv = new ContentValues();
		cv.put(seqColumn, sequence);
		SQLiteDatabase db = getDb();
		db.beginTransactionNonExclusive();
		try {
			if (hasSequence) {
				db.update(seqTableName, cv, whereClause, whereArgs);
			} else {
				cv.put(nameColumn, entityDetails.tableName);
				db.insert(seqTableName, null, cv);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// here you can catch all the exceptions
		} finally {
			db.endTransaction();
			close();
		}
    } 
	
	private boolean existsSequence(String seqTableName, final String whereClause, String[] whereArgs) {
        final String[] selectedColumnList = new String[]{"count(*)"};
        SQLiteDatabase db = getDb();
        Cursor recordCountCursor = db.query(seqTableName, selectedColumnList, whereClause, whereArgs, null, null, null);
        recordCountCursor.moveToFirst();
        int recordCount = recordCountCursor.getInt(0);
        recordCountCursor.close();
        close();
        return recordCount > 0;
	}
}
