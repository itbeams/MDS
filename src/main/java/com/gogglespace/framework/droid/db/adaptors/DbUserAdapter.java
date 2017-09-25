package com.gogglespace.framework.droid.db.adaptors;


import com.gogglespace.framework.droid.SecureDbRepository;
import com.gogglespace.framework.droid.db.entities.UserEntity;

import android.content.Context;

/**
 * Adapter for user information persistence
 * Created by Shahid Nawaz on 12/2/2015.
 * @version 1.0
 */
public class DbUserAdapter extends SecureDbRepository<UserEntity> {

    public DbUserAdapter(Context context){
        super(context, new UserEntity());
    }
}