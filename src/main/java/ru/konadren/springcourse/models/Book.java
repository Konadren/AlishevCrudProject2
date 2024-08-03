package ru.konadren.springcourse.models;

import jakarta.validation.constraints.Pattern;

public class Book {
    private int id;

    private String bookName;

   // @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ A-Z]\\w+")
    private String author;

    private int releaseYear;

    public Book(int id, String name, String author, int releaseYear) {
        this.id = id;
        this.bookName = name;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public Book(){}

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
