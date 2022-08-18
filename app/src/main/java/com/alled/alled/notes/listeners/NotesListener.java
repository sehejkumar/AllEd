package com.alled.alled.notes.listeners;

import com.alled.alled.notes.entities.Note;

public interface NotesListener {

    void onNoteClicked(Note note, int position);
}
