package com.trilogyed.bookservice.ServiceLayer;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteService;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import com.trilogyed.bookservice.viewModel.NoteViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookServiceLayer {

    BookDao bookDao;
    NoteService noteService;
    List<Note> notes;
    List<Note> noteIdList;
    List<Note> notesList;

    @Autowired
    public BookServiceLayer(BookDao bookDao, NoteService noteService) {
        this.bookDao = bookDao;
        this.noteService=noteService;
    }

    @Transactional
    public BookViewModel getBook(int id){

        Book book=bookDao.getBook(id);
        if (book!=null){
            BookViewModel bvm= new BookViewModel();

            bvm.setBookId(book.getBookId());
            bvm.setTitle(book.getTitle());
            bvm.setAuthor(book.getAuthor());
            bvm.setNoteId();


            return bvm;
        }
        return null;
    }




    private BookViewModel buildBookViewModel(Book book){

        notes=noteService.getNoteByBook();
        Note note= new Note();

        for(Note n: notes) {
           noteIdList.add(n.getNoteId());
        }

        BookViewModel bookViewModel= new BookViewModel();
        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNoteId(note.getNoteId());
        bookViewModel.setNote();

        return bookViewModel;
    }

    private NoteViewModel buildNoteViewModel(Note note){

        notes=noteService.getNoteByBook();

        for(Note n: notes) {
            note.setNoteId(n.getNoteId());
            note.setNote(n.getNote());
        }

        NoteViewModel noteViewModel= new NoteViewModel();
        noteViewModel.setNoteId((note.getNoteId()));
        noteViewModel.setNote(note.getNote());
        noteViewModel.setBookId(note.getBookId());

        return noteViewModel;

    }
}
