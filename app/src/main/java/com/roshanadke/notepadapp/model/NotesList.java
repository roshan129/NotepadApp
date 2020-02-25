package com.roshanadke.notepadapp.model;

public class NotesList {

    private String notesText;
    private String notesDate;

    public NotesList(String notesText, String notesDate) {
        this.notesText = notesText;
        this.notesDate = notesDate;
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
