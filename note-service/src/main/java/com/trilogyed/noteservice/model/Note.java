package com.trilogyed.noteservice.model;

import javax.validation.constraints.Size;
import java.util.Objects;

public class Note {
    private int noteId;
    private int bookId;
    @Size(min=1, max=255, message="Note cannot be empty. Note cannot exceed 255 characters.")
    private String note;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return getNoteId() == note1.getNoteId() &&
                getBookId() == note1.getBookId() &&
                getNote().equals(note1.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoteId(), getBookId(), getNote());
    }


}

