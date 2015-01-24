package note.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;

public class Newnote extends Activity {
	EditText newedit;
	private DatabaseHelper db = null;
	private static final String DATABASE_NAME = "NoteList";
	static String FILENAME = "filename";
	static String filenam = "filename";
	Boolean edit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newnote);
		newedit = (EditText) findViewById(R.id.newedit);
		saveDialog();
	}

	void saveDialog() {
		final EditText fileName = new EditText(this);
		fileName.setHint("Enter Filename:");

		new AlertDialog.Builder(this)
				.setTitle("Save")
				.setView(fileName)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int whichButton) {
						filenam = fileName.getText().toString();
						edit = true;

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								finish();
							}
							
						}).show();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		if(edit){
		String fName = "/Note/" + filenam + ".txt";
		Log.e("transpose", "OPENFILELIST:FileName" + fName.toString());
		File file = new File(Environment.getExternalStorageDirectory()
				+ fName.toString());

		try {
			FileWriter fos = new FileWriter(file, true);
			fos.write(newedit.getText().toString());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("note", "NoteList:FileOutputStream:" + e.toString());
		}

		db = new DatabaseHelper(Newnote.this);

		SQLiteDatabase dB = db.getWritableDatabase();

		// Create a new map of values, where column names are
		// the keys
		ContentValues cv = new ContentValues();
		cv.put(FILENAME, filenam);
		dB.insert(DATABASE_NAME, FILENAME, cv);
		}
		else{
			finish();
		}
	}
}
