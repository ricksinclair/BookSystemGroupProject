package com.trilogyed.bookservice.viewModel;

import com.trilogyed.bookservice.model.Note;

import java.util.List;
import java.util.Objects;

public class BookViewModel {

    private int bookId;
    private String title;
    private String author;
    private int noteId;
    private List<Note> note;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return bookId == that.bookId &&
                noteId == that.noteId &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, noteId, note);
    }
}
