package com.roshanadke.notepadapp.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerClickListeners recyclerClickListeners;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerClickListeners recyclerClickListeners){
        this.recyclerClickListeners= recyclerClickListeners;

        gestureDetector= new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && recyclerClickListeners!=null){
                    recyclerClickListeners.onItemLongClick(child,recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }




    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if(child!=null && recyclerClickListeners!=null && gestureDetector.onTouchEvent(e)){
            recyclerClickListeners.onItemClick(child,rv.getChildAdapterPosition(child));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
