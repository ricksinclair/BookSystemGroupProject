package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    NoteDao noteDao;

    @Before
    public void setUp() throws Exception {
        List<Note> noteList = noteDao.getAllNotes();
        for (Note n : noteList) {
            noteDao.deleteNote(n.getNoteId());
        }
    }

    @Test
    public void addGetDeleteNote() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Note number 1");

        noteDao.addNote(note);

        Note note1 = noteDao.getNoteById(note.getNoteId());
        assertEquals(note, note1);

        noteDao.deleteNote(note.getNoteId());
        note1 = noteDao.getNoteById(note.getNoteId());
        assertNull(note1);
    }


    @Test
    public void getAllNotes() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Note number 1");

        noteDao.addNote(note);

        note = new Note();
        note.setBookId(1);
        note.setNote("Note number 2");

        noteDao.addNote(note);

        List<Note> noteList = noteDao.getAllNotes();
        assertEquals(noteList.size(), 2);
    }

    @Test
    public void getNotesByBook() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Note number 1");

        noteDao.addNote(note);

        note = new Note();
        note.setBookId(1);
        note.setNote("Note number 2");

        noteDao.addNote(note);

        List<Note> noteList = noteDao.getNotesByBook(1);
        assertEquals(noteList.size(), 2);

        noteList = noteDao.getNotesByBook(2);
        assertEquals(noteList.size(), 0);
    }

    @Test
    public void updateNote() {
        Note updatedNote = new Note();
        updatedNote.setBookId(1);
        updatedNote.setNote("Note number 1");

        noteDao.addNote(updatedNote);

        updatedNote.setNote("Note number 1 updated");
        noteDao.updateNote(updatedNote);

        Note noteUpdated = noteDao.getNoteById(updatedNote.getNoteId());
        assertEquals(updatedNote, noteUpdated);
    }

}