package ru.konadren.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.konadren.springcourse.dao.wrapper.BookWrapper;
import ru.konadren.springcourse.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate template;

    @Autowired
    public BookDAO(JdbcTemplate template) {
        this.template = template;
    }

    public List<Book> index(){
        return template.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return template.query("SELECT * FROM Books WHERE id=?", new Object[]{id},
                new BookWrapper()
        ).stream().findAny().orElse(null);
    }

    public void save(Book book){
        template.update("INSERT INTO Books(bookName, author, releaseDate) VALUES(?, ?, ?)",
                book.getBookName(), book.getAuthor(), book.getReleaseYear());
    }

    public void update(int id, Book book){
        template.update("UPDATE Book SET bookName=?, author=?, releaseYear=? WHERE id=?",
                book.getBookName(), book.getAuthor(),
                book.getReleaseYear(), id);
    }

    public void delete(int id){
        template.update("DELETE * FROM Books WHERE id=?", id);
    }
}
