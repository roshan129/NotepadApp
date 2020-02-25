package com.roshanadke.notepadapp.model;

public class NotesList {

    private String notesText;
    private String notesDate;
    private String timeInMillis;

    public NotesList(String notesText, String notesDate, String timeInMillis) {
        this.notesText = notesText;
        this.notesDate = notesDate;
        this.timeInMillis = timeInMillis;

    }

    public String getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(String timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public String getNotesText() {
        return notesText;
    }

    public void setNotesText(String notesText) {
        this.notesText = notesText;
    }

    public String getNotesDate() {
        return notesDate;
    }

    public void setNotesDate(String notesDate) {
        this.notesDate = notesDate;
    }
}
