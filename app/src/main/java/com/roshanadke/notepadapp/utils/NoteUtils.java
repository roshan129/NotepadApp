package com.roshanadke.notepadapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteUtils {

    public static String stringFromDate(long time){

        DateFormat dateFormat= new SimpleDateFormat("EEE, MMM dd, yyyy 'at' hh:mm aaa", Locale.US);
        return dateFormat.format(new Date(time));
    }
}
