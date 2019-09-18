package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;

import java.util.List;

public interface NoteDao {
    Note addNote(Note note);

    Note getNoteById(int noteId);

    List<Note> getAllNotes();

    List<Note> getNotesByBook(int bookId);

    void updateNote(Note note);

    void deleteNote(int noteId);
}



