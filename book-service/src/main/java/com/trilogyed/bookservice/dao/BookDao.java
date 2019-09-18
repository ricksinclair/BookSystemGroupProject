package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;

import java.util.List;

public interface BookDao {

    public Book addBook(Book book);

    public Book getBook(int id);

    public List<Book> getAllBooks();

    public void updateBook(Book book);

    public void deleteBook(int id);
}
