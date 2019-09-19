package com.trilogyed.bookservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class BookServiceController {

//    @Autowired
//    private final NoteService noteService;

//    BookServiceController(NoteService noteService){
//        this.noteService=noteService;
//    }

//    @RequestMapping(value = "/books",method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public Book addBook(@RequestBody Book book){
//        return book;
//    }
}
