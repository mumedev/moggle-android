package mume12.mumedev.moggle.db;

import java.util.ArrayList;
import java.util.List;

import mume12.mumedev.moggle.model.QDat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QDatCRUD extends CRUD<QDat> {
	
	public static final String TABLE_NAME 	= "QDat";
	private final String ID 				= "id";
	private final String YEAR 				= "year";
	private final String WEEK 				= "week";
	private final String DAY_OF_WEEK		= "day_of_week";
	private final String WORK_QUANTITY		= "work_quantity";
	private final String WORK_QUALITY		= "work_quality";
	private final String MOOD_QUANTIFIED	= "mood_quantified";

	protected QDatCRUD(DatabaseHandler databaseHandler) {
		super(databaseHandler);
	}

	@Override
	public String getTableName() {
		return "QDat";
	}

	@Override
	public boolean createTable(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + getTableName() + " (" +
				ID 				+ " " + INTEGER + " " + PRIMARY_KEY + " " + AUTOINCREMENT + ", " +
        		YEAR			+ " " + INTEGER + ", " +
        		WEEK			+ " " + INTEGER + ", " +
        		DAY_OF_WEEK		+ " " + INTEGER + ", " +
        		WORK_QUANTITY	+ " " + INTEGER + ", " +
        		WORK_QUALITY	+ " " + INTEGER + ", " +
        		MOOD_QUANTIFIED	+ " " + INTEGER +
        ");");
		return true;
	}

	@Override
	public boolean dropTable(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + getTableName());
		return true;
	}

	@Override
	public long insert(QDat record) {
		SQLiteDatabase db = this.databaseHandler.getWritableDatabase();
		
        return db.insert(getTableName(), null, getContentValues(record));
	}
	
	private ContentValues getContentValues(QDat record) {
		ContentValues values = new ContentValues();
        values.put(YEAR, 				record.getYear());
        values.put(WEEK, 				record.getWeek());
        values.put(DAY_OF_WEEK, 		record.getDayOfWeek());
        values.put(WORK_QUANTITY, 		record.getWorkQuantity());
        values.put(WORK_QUALITY, 		record.getWorkQuality());
        values.put(MOOD_QUANTIFIED,		record.getMoodQuantified());
        
        return values;
	}

	@Override
	public QDat select(long id) {
		SQLiteDatabase db = this.databaseHandler.getReadableDatabase();
	    
	    Cursor cursor = db.query(getTableName(),
	    		new String[] { ID },
	    		ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);

	    if (cursor != null)
	        cursor.moveToFirst();
	    
	    QDat data = setQDat(cursor);
        
        cursor.close();
	    return data;
	}
	
	private QDat setQDat(Cursor cursor) {
		QDat data = new QDat();
        data.setID(cursor.getLong(0));
        data.setYear(cursor.getInt(1));
        data.setWeek(cursor.getInt(2));
        data.setDayOfWeek(cursor.getInt(3));
        data.setWorkQuality(cursor.getInt(4));
        data.setWorkQuantity(cursor.getInt(5));
        data.setMoodQuantified(cursor.getInt(6));
        
        return data;
	}

	@Override
	public List<QDat> select() {
		List<QDat> list = new ArrayList<QDat>();
		
        SQLiteDatabase db = this.databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + getTableName(), null);

        if (cursor.moveToFirst()) {
            do {
                QDat data = setQDat(cursor);
                list.add(data);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        return list;
	}

	@Override
	public boolean update(QDat record) {
		SQLiteDatabase db = this.databaseHandler.getWritableDatabase();
	    
	    int rowsaffected = db.update(getTableName(), getContentValues(record), ID + " = ?",
	            new String[] { String.valueOf(record.getID()) });
	    return rowsaffected > 0;
	}

	@Override
	public boolean delete(long id) {
		SQLiteDatabase db = this.databaseHandler.getWritableDatabase();
	    db.delete(getTableName(), ID + " = ?",
	            new String[] { String.valueOf(id) });
	    return true;
	}

	@Override
	public int recordCount() {
	    SQLiteDatabase db = this.databaseHandler.getReadableDatabase();
	    Cursor cursor = db.rawQuery("SELECT * FROM " + getTableName(), null);
	    int count = cursor.getCount();
	    cursor.close();
	    return count;
	}

}
