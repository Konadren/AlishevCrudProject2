package ru.konadren.springcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "FIO shouldn`t be empty")
    @Size(min = 3, max = 70, message = "Too short or too long FIO")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+$\n")
    private String fio;

    @Min(value = 1900)
    private int birthYear;


    public Person(int id, String fio, int birthYear) {
        this.id = id;
        this.fio = fio;
        this.birthYear = birthYear;
    }

    public Person(){}

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
