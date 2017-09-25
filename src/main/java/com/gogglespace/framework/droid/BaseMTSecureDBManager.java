package com.gogglespace.framework.droid;

import java.util.List;

import com.gogglespace.framework.droid.db.schema.SchemaHelper;

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * Secure multi threaded database manager
 * @author Shahid Nawaz on 02/16/2017
 * @version 1.0
 * */
class BaseMTSecureDBManager extends BasePooledSecureDBManager {
	
	Context context;
	BaseMTSecureDBManager(Context ctx) {
		this(ctx, SchemaHelper.SECURE_DATABASE_NAME, SchemaHelper.SECURE_DATABASE_VERSION);
	}
	
	private BaseMTSecureDBManager(Context context, String dbName, int version) {
		super(context, dbName, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        List<String> createSqlList = SchemaHelper.getSchema(SchemaHelper.SCHEMA_TYPE.CREATE_TABLES, true);
        String secureDbEncoding = "PRAGMA encoding=UTF16"; 
        db.execSQL(secureDbEncoding);
        for (String createSql : createSqlList) {
            db.execSQL(createSql);
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    List<String> dropSqlList = SchemaHelper.getSchema(SchemaHelper.SCHEMA_TYPE.DROP_TABLES, true);
	    for (String dropSql : dropSqlList) {
	        db.execSQL(dropSql);
	    }
	    onCreate(db);
	}
	
}
