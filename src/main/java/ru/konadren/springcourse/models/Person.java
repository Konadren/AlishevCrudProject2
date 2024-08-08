package ru.konadren.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "People")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "FIO shouldn`t be empty")
    @Size(min = 3, max = 70, message = "Too short or too long FIO")
    //@Pattern(regexp = "^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+$\n")
    @Column(name = "fio")
    private String fio;

    @Min(value = 1900)
    @Column(name = "birthYear")
    private int birthYear;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(String fio, int birthYear) {
        this.fio = fio;
        this.birthYear = birthYear;
    }

    public Person(){}

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
