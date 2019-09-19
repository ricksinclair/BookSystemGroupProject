package com.trilogyed.bookservice.serviceLayer;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.util.feign.NoteService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceLayerTest {

    BookDao bookDao;
    BookServiceLayer bookServiceLayer;
    NoteService noteService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void addGetDeleteUpdateBook() {
    }

    @Test
    public void getAllBooks() {
    }


}