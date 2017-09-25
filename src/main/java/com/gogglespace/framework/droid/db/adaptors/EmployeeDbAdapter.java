package com.gogglespace.framework.droid.db.adaptors;

import com.gogglespace.framework.droid.DbRepository;
import com.gogglespace.framework.droid.db.entities.EmployeeEntity;

import android.content.Context;

/**
 * Location / facility adapter to deal with the persistence
 * Created by Shahid Nawaz on 4/10/2015.
 * @see EmployeeEntity
 * @version 1.0
 */
public class EmployeeDbAdapter extends DbRepository<EmployeeEntity> {

    public EmployeeDbAdapter(Context context){
        super(context, new EmployeeEntity());
    }

}
