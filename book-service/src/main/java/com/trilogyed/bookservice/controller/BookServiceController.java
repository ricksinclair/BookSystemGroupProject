package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.serviceLayer.BookServiceLayer;
import com.trilogyed.bookservice.util.feign.NoteService;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
//@CacheConfig(cacheNames ={"books"})
public class BookServiceController {
    public static final String TOPIC_EXCHANGE_NAME = "note-exchange";
    public static final String ROUTING_KEY = "note.add.#";

    @Autowired
    BookServiceLayer bookServiceLayer;
    //feign client
    @Autowired
    RabbitTemplate rabbitTemplate;

    BookServiceController(BookServiceLayer bookServiceLayer, RabbitTemplate rabbitTemplate) {
        this.bookServiceLayer = bookServiceLayer;
        this.rabbitTemplate = rabbitTemplate;
    }

    //    @CachePut(key="#result.getId()")
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookViewModel addBook(@RequestBody BookViewModel book) {
        BookViewModel exists = bookServiceLayer.getBook(book.getBookId());
        if (exists != null)
            throw new IllegalArgumentException("Book " + book.getBookId() + " already exists.");
        bookServiceLayer.addBook(book);
        return book;
    }

    //    @Cacheable
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

    //    @CacheEvict(key="#result.getId()")
    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateBook(@RequestBody BookViewModel book) {
        BookViewModel exists = bookServiceLayer.getBook(book.getBookId());
        if (exists == null)
            throw new IllegalArgumentException("Book " + book.getBookId() + " does not exist.");
        bookServiceLayer.updateBook(book);
    }

    //    @CacheEvict
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(name = "id") int id) {
        bookServiceLayer.deleteBook(id);
    }



//    @RequestMapping(value = "/account", method = RequestMethod.POST)
//    public String createAccount(@RequestBody Account account) {
//        // create message to send to email list creation queue
//        EmailListEntry msg = new EmailListEntry(account.getFirstName() + " " + account.getLastName(), account.getEmail());
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
//        System.out.println("Message Sent");
//
//        // Now do account creation stuff...
//
//        return "Account Created";
//    }
}


