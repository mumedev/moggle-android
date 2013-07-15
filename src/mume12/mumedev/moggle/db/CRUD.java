package mume12.mumedev.moggle.db;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

/**
 * <p>
 * <code>CRUD</code> abstract class. Provides an interface for
 * basic database management methods:
 * <ul>
 * 	<li>Create;</li>
 * 	<li>Read;</li>
 * 	<li>Update;</li>
 * 	<li>Delete.</li>
 * </ul>
 * </p>
 * @author		Joris Schelfaut
 * @version		2012-12-08
 * @since		2012-11-14
 *
 * @param <E>
 */
public abstract class CRUD<E> {
	
	/**
	 * Database handler, connection to the database.
	 */
	protected final DatabaseHandler databaseHandler;
	
	/**
	 * REAL data type.
	 */
    public final String REAL 			= "REAL";
    /**
     * TEXT data type.
     */
    public final String TEXT 			= "TEXT";
    /**
     * INTEGER data type.
     */
    public final String INTEGER			= "INTEGER";
    /**
     * PRIMARY KEY keyword.
     */
    public final String PRIMARY_KEY		= "PRIMARY KEY";
    /**
     * FOREIGN KEY keyword.
     */
    public final String FOREIGN_KEY		= "FOREIGN KEY";
    /**
     * REFERENCES keyword.
     */
    public final String REFERENCES		= "REFERENCES";
    
    /**
     * AUTOINCREMENT keyword.
     */
    public final String AUTOINCREMENT 	= "AUTOINCREMENT";
    
    /**
	 * NULL keyword.
	 */
    public final String NULL 			= "NULL";
    
    /**
	 * NOT keyword.
	 */
    public final String NOT 			= "NOT";
	
    /**
	 * <p>Constructor for the <code>CRUD</code> class.</p>
	 * @param databaseHandler the database handler to access the database.
	 */
	protected CRUD(DatabaseHandler databaseHandler) {
		super();
		this.databaseHandler = databaseHandler;
		this.databaseHandler.addCRUD(getTableName(), this);
	}
	
	/**
	 * <p>Get the name of the table this CRUD is managing.</p>
	 * @return	the name of the table.
	 */
	public abstract String getTableName();

	/**
	 * <p>Create the table the CRUD will be managing.</p>
	 * @return	whether or not the operation was successful.
	 * @param	db an SQLite database.
	 */
	public abstract boolean createTable(SQLiteDatabase db);

	/**
	 * <p>Drop the table the CRUD is be managing.</p>
	 * @return	whether or not the operation was successful.
	 * @param	db an SQLite database.
	 */
	public abstract boolean dropTable(SQLiteDatabase db);

	/**
	 * <p>Insert a new record into the <code>E</code> table</p>.
	 * @param 	record record to be inserted 
	 * @return	the id of the inserted record
	 */
	public abstract long insert(E record);

	/**
	 * <p>Select a record from the table with given id.</p>
	 * <p><code>SELECT record FROM E WHERE record.id = id</code></p>
	 * @param 	id	the id of the record to be returned
	 * @return	the record with given id
	 */
	public abstract E select(long id);
	
	/**
	 * <p>Select all records from the table.</p>
	 * <p><code>SELECT * FROM E</code></p> 
	 * @return	all records from the E table.
	 */
	public abstract List<E> select();
	
	/**
	 * <p>Update a given record.</p>
	 * @param 	record the record to be updated
	 * @return	whether or not the operation was successful.
	 */
	public abstract boolean update(E record);
	
	/**
	 * <p>Delete the record with given id.</p>
	 * @param 	id the id of the record to be deleted
	 * @return	whether or not the operation was successful.
	 */
	public abstract boolean delete(long id);
	
	/**
	 * <p>The number of records in the table the CRUD is managing.</p>
	 * @return	the number of records in this table.
	 */
	public abstract int recordCount();
}
