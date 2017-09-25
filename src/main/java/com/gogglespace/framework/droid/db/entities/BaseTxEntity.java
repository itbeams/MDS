package com.gogglespace.framework.droid.db.entities;

import com.gogglespace.framework.droid.EntityAttributeAnnotation;
import com.gogglespace.framework.droid.FieldType;

/**
 * A base class for transactional data
 * Created by Shahid Nawaz on 4/3/2015.
 * @version 1.0
 */
public class BaseTxEntity extends BaseEntity {

    @EntityAttributeAnnotation(fieldName = "lastUpdated", required = true,  fieldType = FieldType.LONG)
    private long lastUpdated;

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
