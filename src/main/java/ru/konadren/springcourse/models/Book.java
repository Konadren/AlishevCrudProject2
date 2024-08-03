package ru.konadren.springcourse.models;

import jakarta.validation.constraints.Pattern;

public class Book {

    private String bookName;

    @Pattern(regexp = "^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+$\n")
    private String author;
    private int releaseYear;

    public Book(String name, String author, int releaseYear) {
        this.bookName = name;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public Book(){}

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
