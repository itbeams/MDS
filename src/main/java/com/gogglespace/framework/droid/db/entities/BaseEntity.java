package com.gogglespace.framework.droid.db.entities;

import com.gogglespace.framework.droid.EntityAttributeAnnotation;
import com.gogglespace.framework.droid.FieldType;

/**
 * A base class containing information for reference data
 * Created by Shahid Nawaz on 2/3/2015.
 * @version 1.0
 */
public class BaseEntity {

    @EntityAttributeAnnotation(fieldName = "_id", primaryKey = true, required = true, fieldType = FieldType.LONG)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
