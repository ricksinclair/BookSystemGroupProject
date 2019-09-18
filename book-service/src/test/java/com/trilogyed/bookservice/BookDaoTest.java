package com.trilogyed.bookservice;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Before
    public void setUp() throws Exception{

        List<Book> books=bookDao.getAllBooks();

        books.stream()
                .forEach(book -> bookDao.deleteBook(book.getBookId()));
    }

    @Test
    @Transactional
    public void addGetDeleteBook() {
        Book book = new Book("The Gift","Danielle Steel");
        book = bookDao.addBook(book);
        Book fromDao = bookDao.getBook(book.getBookId());
        assertEquals(fromDao, book);
        bookDao.deleteBook(book.getBookId());
        fromDao = bookDao.getBook(book.getBookId());
        assertNull(fromDao);
    }

    @Test
    public void getAllBooks() {
        Book book = new Book("The Dangerous Summer", "Ernest Hemingway");
        bookDao.addBook(book);

        book = new Book("The Lure of Images", "David Morgan");
        bookDao.addBook(book);

        List<Book> books = bookDao.getAllBooks();

        assertEquals(2, books.size());
    }

    @Test
    public void updateRsvp() {
        Book book = new Book("The beauty and the beast", "Unknown");
        book = bookDao.addBook(book);
        book.setTitle("Castle");
        bookDao.updateBook(book);
        Book fromDao = bookDao.getBook(book.getBookId());
        assertEquals(book, fromDao);
    }
}
