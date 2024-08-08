package ru.konadren.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.konadren.springcourse.models.Book;
import ru.konadren.springcourse.models.Person;

import java.util.List;
import java.util.Optional;


public class BookDAO {
//    private final JdbcTemplate template;
//
//    @Autowired
//    public BookDAO(JdbcTemplate template) {
//        this.template = template;
//    }
//
//    public List<Book> index(){
//        return template.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id){
//        return template.query("SELECT * FROM Books WHERE id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//
//    public void save(Book book){
//        template.update("INSERT INTO Books(bookName, author, releaseYear) VALUES(?, ?, ?)",
//                book.getBookName(), book.getAuthor(), book.getReleaseYear());
//    }
//
//    public void update(int id, Book book){
//        template.update("UPDATE Books SET bookName=?, author=?, releaseYear=? WHERE id=?",
//                book.getBookName(), book.getAuthor(),
//                book.getReleaseYear(), id);
//    }
//
//    public void delete(int id){
//        template.update("DELETE FROM Books WHERE id=?", id);
//    }
//
//    public Optional<Person> getBookOwner(int id){
//        return template.query("SELECT People.* FROM Books JOIN People ON Books.person_id = People.id WHERE  Books.id=?",
//        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//

    //todo: ВАЖНЫЕ МЕТОДЫ

//    public void release(int id){
//        template.update("UPDATE Books SET person_id=NULL WHERE id=?", id);
//    }
//
//    public void assign(int id, Person person){
//        template.update("UPDATE Books SET person_id=? WHERE id=?", person.getId(), id);
//    }
}
