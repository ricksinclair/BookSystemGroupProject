package com.trilogyed.bookservice.serviceLayer;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteService;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import com.trilogyed.bookservice.viewModel.NoteViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class BookServiceLayerTest {

    BookDao bookDao;
    BookServiceLayer bookServiceLayer;
    //feign client
    NoteService noteService;

    @Before
    public void setUp() throws Exception {
        setUpBookDaoMocks();
        setUpNoteServiceMocks();
        bookServiceLayer = new BookServiceLayer(bookDao, noteService);
    }

    private void setUpBookDaoMocks() {
        bookDao = mock(BookDao.class);

        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Kill A Mockingbird");
        book.setAuthor("Harper Lee");

        Book book1 = new Book();
        book1.setTitle("Kill A Mockingbird");
        book1.setAuthor("Harper Lee");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Book updatedBook = new Book();
        updatedBook.setBookId(2);
        updatedBook.setTitle("To Kill A Mockingbird");
        updatedBook.setAuthor("Harper Lee");

        Book book3 = new Book();
        book3.setBookId(3);
        book3.setTitle("Atomic Habits");
        book3.setAuthor("James Clear");

        bookList.add(book3);

        doReturn(book).when(bookDao).addBook(book1);
        doReturn(book).when(bookDao).getBook(1);
        doReturn(bookList).when(bookDao).getAllBooks();
        doNothing().when(bookDao).updateBook(updatedBook);
        doReturn(updatedBook).when(bookDao).getBook(2);
        doNothing().when(bookDao).deleteBook(3);
        doReturn(null).when(bookDao).getBook(3);
    }

    private void setUpNoteServiceMocks() {
        noteService = mock(NoteService.class);

        List<Note> noteList = new ArrayList<>();

        Note note = new Note();
        note.setNoteId(1);
        note.setBookId(1);
        note.setNote("In the United States, it has become a classic of modern American literature, winning the Pulitzer Prize.");
        noteList.add(note);

        doReturn(noteList).when(noteService).getNoteByBook(1);
    }

    @Test
    public void addGetDeleteUpdateBook() {
        List<Note> noteList = new ArrayList<>();

        Note note = new Note();
        note.setNoteId(1);
        note.setBookId(1);
        note.setNote("In the United States, it has become a classic of modern American literature, winning the Pulitzer Prize.");
        noteList.add(note);

        BookViewModel book = new BookViewModel();
        book.setTitle("Kill A Mockingbird");
        book.setAuthor("Harper Lee");
        book.setNotes(noteList);

        book = bookServiceLayer.addBook(book);
        BookViewModel fromService = bookServiceLayer.getBook(book.getBookId());
        assertEquals(book, fromService);

        book = new BookViewModel();

        Book updatedBook = new Book();
        updatedBook.setBookId(2);
        updatedBook.setTitle("To Kill A Mockingbird");
        updatedBook.setAuthor("Harper Lee");

        book.setBookId(updatedBook.getBookId());
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());

        bookServiceLayer.updateBook(book);

        fromService = bookServiceLayer.getBook(2);

        Book book3 = new Book();
        book3.setBookId(fromService.getBookId());
        book3.setTitle(fromService.getTitle());
        book3.setAuthor(fromService.getAuthor());

        assertEquals(book3, updatedBook);

        bookServiceLayer.deleteBook(3);
        fromService = bookServiceLayer.getBook(3);
        assertNull(fromService);
    }


}