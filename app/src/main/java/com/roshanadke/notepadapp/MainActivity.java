package com.roshanadke.notepadapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roshanadke.notepadapp.adapters.CustomAdapter;
import com.roshanadke.notepadapp.db.DatabaseHelper;
import com.roshanadke.notepadapp.listeners.RecyclerClickListeners;
import com.roshanadke.notepadapp.listeners.RecyclerTouchListener;
import com.roshanadke.notepadapp.model.NotesList;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    List<NotesList> mainList;
    DatabaseHelper db;
    AlertDialog.Builder builder;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddNote.class);
                startActivity(intent);

            }
        });

        mainList = new ArrayList<>();
        db = new DatabaseHelper(this);
        fetchList();
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        customAdapter = new CustomAdapter(this, mainList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

        onClickImplementations();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchList();
    }

    private void fetchList() {
        if (mainList != null) {
            mainList.clear();
        }
        Cursor c = db.showData();
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                String Text1 = c.getString(1);
                String Date1 = c.getString(2);
                String time = c.getString(3);
                NotesList obj1 = new NotesList(Text1, Date1, time);
                mainList.add(obj1);
                Log.e("mainlist", mainList.toString());

            } while (c.moveToNext());

        } else {
            Toast.makeText(this, "No data found for list", Toast.LENGTH_SHORT).show();
        }

        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
            System.out.println("Adapter " + customAdapter.toString());
        }
    }

    private void onClickImplementations() {

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerClickListeners() {
            @Override
            public void onItemClick(View view, int position) {

                NotesList obj1 =  mainList.get(position);
                Intent intent=new Intent(MainActivity.this, AddNote.class);
                String time= obj1.getTimeInMillis();
                Log.d(TAG, "onItemClick: "+ time);
                intent.putExtra("timeinmillis",time);
                startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int position) {

                alertDiaologShow(position);

            }
        }));

    }

    private void alertDiaologShow(final int pos) {

        String[] array= {"Delete"};
        builder= new AlertDialog.Builder(this);

        builder.setTitle("Select Operation");
        builder.setItems(array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case 0:
                        noteDelete(pos);
                        break;

                        default:
                            break;
                }
            }
        });
        builder.show();
    }

    private void noteDelete(int pos) {

        NotesList obj= mainList.get(pos);
        db.deleteData(obj.getNotesText());
        Toast.makeText(this, "data deleted", Toast.LENGTH_SHORT).show();
        fetchList();
        customAdapter.notifyDataSetChanged();
    }


}

