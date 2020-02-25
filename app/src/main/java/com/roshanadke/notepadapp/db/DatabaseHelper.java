package com.roshanadke.notepadapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.roshanadke.notepadapp.model.NotesList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="notepad12.db";
    private static final String TABLE_NAME="notes";
    private static final String KEY_NOTE= "NOTES_TEXT";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+TABLE_NAME+"(NOTEID INTEGER PRIMARY KEY AUTOINCREMENT,NOTES_TEXT TEXT,NOTES_DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public  boolean insertData(NotesList object)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTES_TEXT",object.getNotesText());
        contentValues.put("NOTES_DATE",object.getNotesDate());

        Log.d("SSSS",object.getNotesText());

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (result==-1)
            return  false;
        else
            return true;
    }

    //==============Select data============================
    public Cursor getData(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        String query ="SELECT * FROM " +TABLE_NAME+ " WHERE CATEGORY LIKE '" + id + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        return cursor;
    }


    public Cursor showData()
    {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //  String query =("SELECT * FROM " +TABLE_NAME+ );
        String query="SELECT * FROM " +TABLE_NAME;

        return sqLiteDatabase.rawQuery(query,null);
    }
    public void deleteData(String note1)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        int result= sqLiteDatabase.delete(TABLE_NAME,KEY_NOTE + " =? ", new String[]{note1});

        Log.d("delete result", ""+ result);

    }

}
