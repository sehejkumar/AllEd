package com.example.alled.notes.listeners;

import com.example.alled.notes.entities.Note;

public interface NotesListener {

    void onNoteClicked(Note note, int position);
}
