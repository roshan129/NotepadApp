package com.roshanadke.notepadapp.adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roshanadke.notepadapp.R;
import com.roshanadke.notepadapp.model.NotesList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Context context;
    private List<NotesList> notesList;

    public CustomAdapter(Context context, List<NotesList> notesList) {
        this.context = context;
        this.notesList = notesList;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.list_layout,parent,false);

        return new CustomViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.notesText.setText(notesList.get(position).getNotesText());
        holder.notesDate.setText(notesList.get(position).getNotesDate());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView notesText,notesDate;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            notesText= itemView.findViewById(R.id.txtv_title_text);
            notesDate= itemView.findViewById(R.id.txtv_date_time);

        }

    }



}

