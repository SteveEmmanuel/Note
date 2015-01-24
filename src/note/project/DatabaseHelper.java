package note.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME="NoteList";
	static final String FILENAME="filename";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("notes", "DATABASEHELPER:oncreate");
		db.execSQL("CREATE TABLE IF NOT EXISTS NoteList (_id INTEGER PRIMARY KEY AUTOINCREMENT,filename TEXT);");
		
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void onOpen(SQLiteDatabase db) {
		onCreate(db);
		
	}
	
}