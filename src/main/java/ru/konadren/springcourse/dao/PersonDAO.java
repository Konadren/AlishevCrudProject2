package ru.konadren.springcourse.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.konadren.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate template;

    @Autowired
    public PersonDAO(JdbcTemplate template) {
        this.template = template;
    }

    public List<Person> index(){
        return template.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return template.query(
                "SELECT * FROM Person WHERE id=?", new Object[]{id},
                new PersonMapper()
        ).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        template.update("INSERT INTO Person(fio, birthYear) VALUES(?, ?)",
                person.getFio(), person.getBirthYear());
    }

    public void update(int id, Person person) {
        template.update("UPDATE Person SET fio=?, birthYear=? WHERE id=?",
                person.getFio(), person.getBirthYear(), id);
    }

    public void delete(int id){
        template.update("DELETE FROM Person WHERE id=?", id);
    }

}
