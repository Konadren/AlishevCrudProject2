package ru.konadren.springcourse.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    @GetMapping
    public String startPage(){
        return "librarian/startPage";
    }

}
