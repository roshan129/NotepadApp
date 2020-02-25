package com.roshanadke.notepadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.roshanadke.notepadapp.db.DatabaseHelper;
import com.roshanadke.notepadapp.model.NotesList;
import com.roshanadke.notepadapp.utils.NoteUtils;

import java.util.Date;

public class AddNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText input_note;
    long date;
    DatabaseHelper databaseHelper;


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
        if (getIntent().hasExtra("text") && getIntent().hasExtra("date")) {
            Bundle bundle = getIntent().getExtras();
            String text = bundle.getString("text");
            String date = bundle.getString("date");

            loadNotes(text, date);
        }

    }

    private void loadNotes(String text, String date) {

        input_note.setText(text);

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

        String noteText = input_note.getText().toString();
        if (noteText != "" && noteText != null) {
            date = new Date().getTime();
            String dateTime = NoteUtils.stringFromDate(date);
            NotesList notesListObject = new NotesList(noteText, dateTime);
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
