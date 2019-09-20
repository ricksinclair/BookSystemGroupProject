package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.model.Note;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteService {

    @RequestMapping(value = "/notes/book/{bookId}", method = RequestMethod.GET)
    public List<Note> getNoteByBook(@PathVariable("bookId") int bookId);

    @RequestMapping(value="/notes/{noteId}", method = RequestMethod.GET)
    public Note getNoteById(@PathVariable("noteId") int noteId);

}
