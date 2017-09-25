package com.gogglespace.framework.droid;

import java.util.List;

import com.gogglespace.framework.droid.db.schema.SchemaHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * None Secure multi threaded database manager
 * @author Shahid Nawaz on 02/16/2017
 * @version 1.0
 * */
class BaseMTDBManager extends BasePooledDBManager {
	
	Context context;
	BaseMTDBManager(Context ctx) {
		this(ctx, SchemaHelper.REGULAR_DATABASE_NAME, SchemaHelper.REGULAR_DATABASE_VERSION);
	}
	
	private BaseMTDBManager(Context context, String dbName, int version) {
		super(context, dbName, version);
		this.context = context;
	}

	@Override
	void onCreate(SQLiteDatabase db) {
        List<String> createSqlList = SchemaHelper.getSchema(SchemaHelper.SCHEMA_TYPE.CREATE_TABLES, false);
        for (String createSql : createSqlList) {
            db.execSQL(createSql);
        }
	}

	@Override
	void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        List<String> dropSqlList = SchemaHelper.getSchema(SchemaHelper.SCHEMA_TYPE.DROP_TABLES, false);
        for (String dropSql : dropSqlList) {
            db.execSQL(dropSql);
        }
        onCreate(db);	
	}
	
}
