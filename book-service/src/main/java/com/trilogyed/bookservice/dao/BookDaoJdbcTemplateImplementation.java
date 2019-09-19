package com.trilogyed.bookservice.dao;


import com.trilogyed.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImplementation implements BookDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT_BOOK =
            "insert into book (title, author) values (?, ?)";
    private static final String SELECT_BOOK =
            "select * from book where book_id = ?";
    private static final String SELECT_ALL_BOOKS =
            "select * from book";
    private static final String UPDATE_BOOK =
            "update book set title = ?, author = ? where book_id = ?";
    private static final String DELETE_BOOK =
            "delete from book where book_id = ?";

    public BookDaoJdbcTemplateImplementation(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Book addBook(Book book) {
        jdbcTemplate.update(INSERT_BOOK,
                book.getTitle(),
                book.getAuthor());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        book.setBookId(id);
        return book;    }

    @Override
    public Book getBook(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException ex) {
            // if nothing is returned for this query, just return null
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {

        return jdbcTemplate.query(SELECT_ALL_BOOKS, this::mapRowToBook);
    }

    @Override
    public void updateBook(Book book) {

        jdbcTemplate.update(UPDATE_BOOK,
                book.getTitle(),
                book.getAuthor(),
                book.getBookId());
    }

    @Override
    public void deleteBook(int id) {

        jdbcTemplate.update(DELETE_BOOK, id);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        return book;
    }
}
