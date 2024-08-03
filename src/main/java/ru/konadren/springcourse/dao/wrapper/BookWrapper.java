package ru.konadren.springcourse.dao.wrapper;

import org.springframework.jdbc.core.RowMapper;
import ru.konadren.springcourse.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookWrapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet result, int rowNum) throws SQLException {
        Book book = new Book();

        book.setAuthor(result.getString("author"));
        book.setBookName(result.getString("bookName"));
        book.setReleaseYear(result.getInt("releaseYear"));

        return book;
    }
}
