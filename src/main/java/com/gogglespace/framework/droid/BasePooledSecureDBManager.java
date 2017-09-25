package com.gogglespace.framework.droid;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * {@link https://github.com/JakarCo/databasemanager }
 * Extend this class and use it as an SQLiteOpenHelper class
 * If distributing: Keep my notes that are self-promoting. Keep or improve the other notes.
 * If distributing (to programmers) in a way that the notes cannot be read, please include a readme file and provide
 * a link to http://androidsqlitelibrary.com
 * You don't have to keep the self-promotion stuff and you don't have to keep the link in a readme, but I would appreciate it.
 * If you ever need help with this code, contact me at support@androidsqlitelibrary.com (or support@jakar.co )
 *
 * Do not sell this. but use it as much as you want. There are no implied or express warranties with this code.
 *
 * This is a simple database manager class which makes threading/synchronization super easy.
 *
 * Extend this class and use it like an SQLiteOpenHelper, but use it as follows:
 *  Instantiate this class once in each thread that uses the database.
 *  Make sure to call {@link #close()} on every opened instance of this class
 *  If it is closed, then call {@link #open()} before using again.
 *
 * Call {@link #open()} to get an instance of the underlying SQLiteDatabse class (which is synchronized).
 * I also implement this system (well, it's very similar) in my <a href="http://androidslitelibrary.com">Android SQLite Libray</a> at http://androidslitelibrary.com
 */
abstract class BasePooledSecureDBManager {
    static String key = "";
    /**See SQLiteOpenHelper documentation
    */
    abstract public void onCreate(SQLiteDatabase db);
    /**See SQLiteOpenHelper documentation
     */
    abstract public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
    /**Optional.
     * *
     */
    public void onOpen(SQLiteDatabase db){}
    /**Optional.
     *
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    /**Optional
     *
     */
    public void onConfigure(SQLiteDatabase db){}



	/** The SQLiteOpenHelper class is not actually used by your application.
     *
     */
    static private class DBSQLiteOpenHelper extends SQLiteOpenHelper {

        BasePooledSecureDBManager databaseManager;
        private AtomicInteger counter = new AtomicInteger(0);

        public DBSQLiteOpenHelper(Context context, String name, int version, BasePooledSecureDBManager databaseManager) {
            super(context, name, null, version);
            this.databaseManager = databaseManager;
        }

        public void addConnection(){
            counter.incrementAndGet();
        }
        public void removeConnection(){
            counter.decrementAndGet();
        }
        public int getCounter() {
            return counter.get();
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            databaseManager.onCreate(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            databaseManager.onUpgrade(db, oldVersion, newVersion);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            databaseManager.onOpen(db);
        }

////        @Override
//        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            databaseManager.onDowngrade(db, oldVersion, newVersion);
//        }

//        //@Override
//        public void onConfigure(SQLiteDatabase db) {
//            databaseManager.onConfigure(db);
//        }
    }

    private static final ConcurrentHashMap<String,DBSQLiteOpenHelper> dbMap = new ConcurrentHashMap<String, DBSQLiteOpenHelper>();

    private static final Object lockObject = new Object();


    private DBSQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase db;
    private boolean isActive;

    /** Instantiate a new DB Helper.
     * <br> SQLiteOpenHelpers are statically cached so they (and their internally cached SQLiteDatabases) will be reused for concurrency
     *
     * @param context Any {@link android.content.Context} belonging to your package.
     * @param name The database name. This may be anything you like. Adding a file extension is not required and any file extension you would like to use is fine.
     * @param version the database version.
     */
    public BasePooledSecureDBManager(Context context, String name, int version) {
        String dbPath = context.getApplicationContext().getDatabasePath(name).getAbsolutePath();
        synchronized (lockObject) {
            sqLiteOpenHelper = dbMap.get(dbPath);
            if (sqLiteOpenHelper==null) {
                sqLiteOpenHelper = new DBSQLiteOpenHelper(context, name, version, this);
                dbMap.put(dbPath,sqLiteOpenHelper);
            }
			//SQLiteOpenHelper class caches the SQLiteDatabase, so this will be the same SQLiteDatabase object every time
            db = sqLiteOpenHelper.getWritableDatabase(getCryptoKey(context));
        }
        this.isActive = true;
    }
    /**
     * Alias for {@link #open()}, so see that method
     */
	public SQLiteDatabase getDb(Context context){
		return open(context);
	}

    /** Check if the underlying SQLiteDatabase is open
     *
     * @return whether the DB is open or not (checks for null, to prevent crashing)
     */
    public boolean isOpen(){
        return (db!=null&&db.isOpen());
    }


    /** Lowers the DB counter by 1 for any {@link BasePooledSecureDBManager}s referencing the same DB on disk
     *  <br />If the new counter is 0, then the database will be closed.
     *  <br /><br />This needs to be called before application exit.
     *
     * @return true if the underlying {@link android.database.sqlite.SQLiteDatabase} is closed (counter is 0), and false otherwise (counter > 0)
     */
    public boolean close(){
    	if (this.isActive){
    		sqLiteOpenHelper.removeConnection();
    	}
    	int count = sqLiteOpenHelper.getCounter();
    	if (count==0){
    	    synchronized (lockObject){
                if (db.inTransaction())db.endTransaction();
                if (db.isOpen())db.close();
                db = null;
            }
    	}
       return (count==0);
    }
	/** Increments the internal db counter by one and opens the db if needed.
	 *
	*
	* @return db SQLiteDatabase object which is readable and writable
	*/
	public SQLiteDatabase open(Context context){
		if (!this.isActive){
			sqLiteOpenHelper.addConnection();
		}
		if (db==null||!db.isOpen()){
			synchronized (lockObject){
	          		db = sqLiteOpenHelper.getWritableDatabase(getCryptoKey(context));
	        	 }
		}
		this.isActive = true;
		return db;
	}

	private String getCryptoKey(Context context) {
		if ( key == null || key.length() == 0) {
			key = "";
		}
		return key;
	}

}
