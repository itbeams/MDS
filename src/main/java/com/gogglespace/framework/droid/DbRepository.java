package com.gogglespace.framework.droid;
/**
 *  Droid Persistence Framework DB repository supports 
 * <br />It is a generic repository class for db adapters to make all operations
 * @author Shahid Nawaz on 2/4/2015.
 * @version 1.0
 */
    public DbRepository(Context context, T o) {
        super(context, o);
    }

     * Get a list of entity objects of specified return type matching given criteria
     * @return List<T> - where T is of type entity caller is assigned to
     * */
    @SuppressWarnings({ "hiding", "unchecked" })
	public <T> List<T> getAll(Criteria criteria){
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        EntityInstanceFactory instanceFactory = new EntityInstanceFactory();
        String whereClause = helper.getWhereClause(criteria);
        String[] whereArgs = helper.getWhereArgs(criteria);
        String orderByClause = helper.getOrderByClause(criteria);
        List<T> entities = new ArrayList<>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                T entity = (T) instanceFactory.getInstance(type);
                for(FieldInfo f : entityDetails.fields) {
                    Object objVal;
                    FieldType fieldType = f.getFieldType();

                    String fType = fieldType.name();
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
        }
    }

    /**
     * Retrieve an entity by Id (pk)
     * @param id - pk
     * @return T - entity of caller type
     * */
    @SuppressWarnings({ "hiding", "unchecked" })
	public <T> T get(long id) {
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        SQLiteDatabase db = this.getDb();
        
        EntityInstanceFactory instanceFactory = new EntityInstanceFactory();
        T entity = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if(cursor.isFirst()){
                entity = (T) instanceFactory.getInstance(type);
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
                        case DataType.TYPE_INT: 
                            objVal = cursor.getInt(cursor.getColumnIndex(f.getColumnName()));
                            break;
                        default: 
                            objVal = cursor.getString(cursor.getColumnIndex(f.getColumnName()));
                            break;
                    }
                    ObjectUtils.findPut(entity, entity.getClass(), f.getFieldName(), objVal, entityFieldCache);
                cursor.moveToNext();
            }
            cursor.close();
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
	                update = false;
	                continue;
            }
            cv.put(f.getColumnName(), StringUtils.getString(f.getData()));
        }
        
        if (!update) {
            long id = getDb().insert(tableName, null, cv);
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setId(id);
        } else {
        }
        close();
        return obj;
    }

    /**
     * Delete by provided criteria
     * @param criteria - delete entity/entities based on criteria
     * @return INT - returns -1 for deletion failed and positive value 1 otherwise
     * */
	public int delete(Criteria criteria){
        int status = -1;
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        String whereClause = helper.getWhereClause(criteria);
        String[] whereArgs = helper.getWhereArgs(criteria);
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
	public <T> int delete(T obj){
        int status = -1;
        DbRepositoryHelper helper = new DbRepositoryHelper();
        DbRepositoryHelper.EntityDetails entityDetails =  helper.getEntityDetailsOnly(type);
        BaseEntity baseEntity = (BaseEntity) obj;
        if (execStatus) {
            status = 1;
        }
        close();
        return status;
    }

   