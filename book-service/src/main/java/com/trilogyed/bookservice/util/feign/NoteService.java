package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteService {

    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    public List<Note> getNoteByBook();

}
