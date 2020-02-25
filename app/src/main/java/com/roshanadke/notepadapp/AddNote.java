package com.roshanadke.notepadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.roshanadke.notepadapp.db.DatabaseHelper;
import com.roshanadke.notepadapp.model.NotesList;
import com.roshanadke.notepadapp.utils.NoteUtils;

import java.util.Currency;
import java.util.Date;

public class AddNote extends AppCompatActivity {
    private static final String TAG = "AddNote";
    Toolbar toolbar;
    EditText input_note;
    long date;
    DatabaseHelper databaseHelper;

    private boolean flag;
    private String existingTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        databaseHelper = new DatabaseHelper(this);
        input_note = findViewById(R.id.input_note);

        getIntentComponents();
    }

    private void getIntentComponents() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            existingTimestamp = bundle.getString("timeinmillis");
            if (existingTimestamp != null) {
                Log.d(TAG, "getIntentComponents: " + existingTimestamp);
                loadNotes(existingTimestamp);
                flag = true;
            }
        } else {
            flag = false;
        }

    }

    private void loadNotes(String existingTimestamp) {
        Cursor c = databaseHelper.getText(existingTimestamp);
        if (c.getCount() != 0) {
            c.moveToFirst();

            String note = c.getString(c.getColumnIndex("NOTES_TEXT"));
            input_note.setText(note);
            input_note.setSelection(input_note.getText().length());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.save_note) {
            onSaveNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        if (flag) {
            String noteText = input_note.getText().toString();
            if (!noteText.equals("")) {
                date = new Date().getTime();
                String dateTime = NoteUtils.stringFromDate(date);

                NotesList notesListObject = new NotesList(noteText, dateTime, existingTimestamp);
                boolean result = databaseHelper.updateData(notesListObject);
                if (result) {
                    Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "error occured", Toast.LENGTH_SHORT).show();
                }

                finish();
            } else {
                Toast.makeText(this, "Enter note", Toast.LENGTH_SHORT).show();
            }

        } else {
            String noteText = input_note.getText().toString();
            if (!noteText.equals("")) {
                date = new Date().getTime();
                String dateTime = NoteUtils.stringFromDate(date);
                String time = String.valueOf(System.currentTimeMillis());
                NotesList notesListObject = new NotesList(noteText, dateTime, time);
                boolean result = databaseHelper.insertData(notesListObject);
                if (result) {
                    Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "error occured", Toast.LENGTH_SHORT).show();
                }

                finish();
            } else {
                Toast.makeText(this, "Enter note", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
