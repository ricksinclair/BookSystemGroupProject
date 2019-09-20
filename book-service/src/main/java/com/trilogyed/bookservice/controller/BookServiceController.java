package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.serviceLayer.BookServiceLayer;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class BookServiceController {

    @Autowired
    BookServiceLayer bookServiceLayer;

    BookServiceController(BookServiceLayer bookServiceLayer) {
        this.bookServiceLayer = bookServiceLayer;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookViewModel addBook(@RequestBody BookViewModel book) {
        BookViewModel exists = bookServiceLayer.getBook(book.getBookId());
        if (exists != null)
            throw new IllegalArgumentException("Book " + book.getBookId() + " already exists.");
        bookServiceLayer.addBook(book);
        return book;
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public BookViewModel getBook(@PathVariable(name = "id") int id) {
        BookViewModel book = bookServiceLayer.getBook(id);
        if (book == null)
            throw new NotFoundException("Book could not be found");
        return book;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        return bookServiceLayer.getAllBooks();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateBook(@RequestBody BookViewModel book) {
        BookViewModel exists = bookServiceLayer.getBook(book.getBookId());
        if (exists == null)
            throw new IllegalArgumentException("Book " + book.getBookId() + " does not exist.");
        bookServiceLayer.updateBook(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(name = "id") int id) {
        bookServiceLayer.deleteBook(id);
    }
}


