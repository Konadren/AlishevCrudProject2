package ru.konadren.springcourse.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bookName")
    private String bookName;

   // @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ A-Z]\\w+")
    @Column(name = "author")
    private String author;

    @Column(name = "releaseYear")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    public Person owner;

    public Book(String name, String author, int releaseYear) {
        this.bookName = name;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public Book(){}

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
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
