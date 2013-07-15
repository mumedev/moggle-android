package mume12.mumedev.moggle.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <p>
 * The <code>DatabaseHandler</code> class manages a list of
 * CRUD objects that provide an interface
 * to manage all each table in the database.
 * Table CRUDs can be added dynamically. 
 * </p>
 * 
 * @author		Joris Schelfaut
 * @since		2012-11-09
 * @version		2012-12-08
 */
public class DatabaseHandler extends SQLiteOpenHelper implements Iterable<CRUD<? extends Object>> {
	
	/**
	 * Map with key-value pairs (tableName, TableCRUD).
	 */
	private final Map<String, CRUD<? extends Object>> tables = new HashMap<String, CRUD<? extends Object>>();
	
	private QDatCRUD qDatCRUD;
	
	/**
	 * <p>Instantiates a new <code>DatabaseHandler</code> object
	 * with database name "Moggle" and database version 1.</p>
	 * @param context	the context
	 */
	public DatabaseHandler(Context context) {
		this(context, "Moggle", 1);
	}
	
	/**
	 * <p>Instantiates a new <code>DatabaseHandler</code> object.</p>
	 * @param context			the context
	 * @param databaseName		the name of the database
	 * @param databaseVersion	the version of the database
	 */
	public DatabaseHandler(Context context, String databaseName, int databaseVersion) {
		super(context, databaseName, null, databaseVersion);		
		this.qDatCRUD = new QDatCRUD(this);
	}
	
	/**
	 * <p>Add a CRUD to manage the corresponding table to the database handler.</p>
	 * @param tableName the table name
	 * @param crud		the CRUD that manages the corresponding table
	 */
	protected void addCRUD(String tableName, CRUD<? extends Object> crud) {
		this.tables.put(tableName, crud);
	}
	
	/**
	 * <p>Remove a CRUD that manages the corresponding table to the database handler.</p>
	 * @param tableName the table name
	 */
	protected void removeCRUD(String tableName) {
		this.tables.remove(tableName);
	}
	
	/**
	 * <p>Get the CRUD for the corresponding table name.</p>
	 * @param 	tableName	the table name
	 * @return	the CRUD for the corresponding table name
	 */
	public CRUD<? extends Object> getTable(String tableName) {
		return tables.get(tableName);
	}

	/**
	 * <p>Close any open database object.</p>
	 */
	public void closeTransaction() {
		this.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		getQDatCRUD().createTable(db);
		/*for (CRUD<? extends Object> c : tables.values()) {
			c.createTable(this.getWritableDatabase());
		}*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		getQDatCRUD().dropTable(this.getWritableDatabase());
		getQDatCRUD().createTable(this.getWritableDatabase());
		/*for (CRUD<? extends Object> c : tables.values()) {
			c.dropTable(this.getWritableDatabase());
		}
		
		for (CRUD<? extends Object> c : tables.values()) {
			c.createTable(this.getWritableDatabase());
		}*/
	}

	@Override
	public Iterator<CRUD<? extends Object>> iterator() {
		return this.tables.values().iterator();
	}
	
	public QDatCRUD getQDatCRUD() {
		return qDatCRUD;
	}
}
