package note.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends Activity {
	EditText Text;
	Button Edit;
	Boolean edit=false;
	String fName;
	File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		Text=(EditText)findViewById(R.id.Text);
		Edit=(Button)findViewById(R.id.edit);
	
		
		Bundle bundle = this.getIntent().getExtras();
		String fileName=bundle.getString("filename");
		
		fName = Environment.getExternalStorageDirectory()
				+ "/Note/" + fileName + ".txt";
		file = new File(fName);
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuilder textBuilder = new StringBuilder();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				textBuilder.append(line);
				textBuilder.append("\n");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Text.setText(textBuilder);
		Text.setEnabled(false);
		
		Edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Text.setEnabled(true);
				edit=true;
							}
		});;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		if(edit){
			try {
				FileWriter fos = new FileWriter(file, false);
				fos.write(Text.getText().toString());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("note", "NoteList:FileOutputStream:" + e.toString());
			}

		}
	finish();
		}
}
