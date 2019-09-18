package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.dao.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteServiceController {

    @Autowired
    NoteDao noteDao;

    public NoteServiceController(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
}
