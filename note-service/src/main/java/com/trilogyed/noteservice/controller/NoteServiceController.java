package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteServiceController {

    @Autowired
    NoteDao noteDao;

    public NoteServiceController(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @RequestMapping(value="/notes", method= RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Note addNote(@RequestBody @Valid Note note) {
        Note exists = noteDao.getNoteById(note.getNoteId());
        if(exists != null)
            throw new IllegalArgumentException("Note " + note.getNoteId() + " already exists.");
        noteDao.addNote(note);
        return note;
    }

    @RequestMapping(value ="/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNoteById(@PathVariable (name="id") int noteId) {
        try {
            return noteDao.getNoteById(noteId);
        } catch (EmptyResultDataAccessException e ) {
            return null;
        }
    }

    @RequestMapping(value="/notes/book/{bookId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNotesByBook(@PathVariable(name="bookId") int bookId) {
        return noteDao.getNotesByBook(bookId);
    }

    @RequestMapping(value="/notes", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @RequestMapping(value="/notes/{id}", method= RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateNote(@RequestBody Note note){
        noteDao.updateNote(note);
    }

    @RequestMapping(value="/notes/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable(name="id") int noteId) {
        noteDao.deleteNote(noteId);
    }
}

