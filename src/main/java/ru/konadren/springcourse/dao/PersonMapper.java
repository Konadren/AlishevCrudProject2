package ru.konadren.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.konadren.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet result, int rowNum) throws SQLException {
        Person person = new Person();
        person.setFio(result.getString("fio"));
        person.setBirthYear(result.getInt("birthYear"));

        return person;
    }
}
