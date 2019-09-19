package com.trilogyed.bookservice.serviceLayer;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteService;
import com.trilogyed.bookservice.viewModel.BookViewModel;
import com.trilogyed.bookservice.viewModel.NoteViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookServiceLayer {

    BookDao bookDao;
    NoteService noteService;

    @Autowired
    public BookServiceLayer(BookDao bookDao, NoteService noteService) {
        this.bookDao = bookDao;
        this.noteService = noteService;
    }

    public BookViewModel getBook(int id) {
        Book book = bookDao.getBook(id);
        if (book == null) {
            return null;
        } else {
            return buildBookViewModel(book);
        }
    }

    public List<BookViewModel> getAllBooks() {
        List<Book> bookList = bookDao.getAllBooks();
        List<BookViewModel> bvmList = new ArrayList<>();

        for (Book b : bookList) {
            bvmList.add(buildBookViewModel(b));
        }
        return bvmList;
    }

    @Transactional
    public BookViewModel addBook(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setBookId(bookViewModel.getBookId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());

        book = bookDao.addBook(book);

        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNotes(noteService.getNoteByBook(bookViewModel.getBookId()));

        return bookViewModel;
    }

    public void updateBook(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setBookId(bookViewModel.getBookId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());

        bookDao.updateBook(book);
    }

    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }

    private BookViewModel buildBookViewModel(Book book) {

        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNotes(noteService.getNoteByBook(book.getBookId()));
        return bookViewModel;
    }

    private NoteViewModel buildNoteViewModel(Note note) {

        NoteViewModel noteViewModel = new NoteViewModel();
        noteViewModel.setNoteId((note.getNoteId()));
        noteViewModel.setNote(note.getNote());
        noteViewModel.setBookId(note.getBookId());

        return noteViewModel;

    }
}
