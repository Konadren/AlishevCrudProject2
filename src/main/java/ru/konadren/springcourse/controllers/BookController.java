package ru.konadren.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.konadren.springcourse.models.Book;
import ru.konadren.springcourse.models.Person;
import ru.konadren.springcourse.services.BooksService;
import ru.konadren.springcourse.services.PeopleService;

import java.util.Optional;

@Controller
@RequestMapping("/librarian/books")
public class BookController {

    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public BookController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    // если на /people нажать на кнопку "добавить человека"
    // совершается переход на страницу addingPage
    @GetMapping("/newBook")
    public String goToAddingPage(@ModelAttribute("book") Book book){
        return "books/addingPage";
    }

    // при клике на "сохранить человека" вызывается этот метод из addingPage
    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult binding){
        //todo: валидатор
        if (binding.hasErrors()) return "/books/addingPage";
        //todo: сохранение человека (метод из ДАО)
        booksService.save(book);
        return "redirect:/librarian/books";
    }


    // просто показываем человека и книги закрепленные за ним
    @GetMapping("/{id}")
    public String showCurrentBook(@PathVariable("id") Integer id, Model model,
                                  @ModelAttribute("person") Person person){
        model.addAttribute("book", booksService.findOne(id));

        // todo: сделать метод в репозитории, а потом использовать в сервисе
        Optional<Person> bookOwner = booksService.getBookOwner(id);
        
        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else 
            model.addAttribute("people", peopleService.findAll());
        
        return "books/currentBookPage";
    }


    // с currentPersonPage при клике на кнопку совершается переход на editPage
    @GetMapping("/{id}/edit")
    public String goToEditPersonPage(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksService.findOne(id));
        return  "books/editPage";
    }

    // обновляем данные о человеке после перехода на editPage
    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult binding, @PathVariable("id") int id){

        if (binding.hasErrors()) return "books/editPage";
        booksService.update(id, book);
        return "redirect:/librarian/books";
    }

    // на currentPersonPage кнопка delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/librarian/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person){

        //todo: сделать метод в репозитории и добавить в сервис
        booksService.assign(id, person);
        return "redirect:/librarian/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){

        //todo: сделать метод в репозитории и добавить в сервис
        booksService.release(id);
        return "redirect:/librarian/books/" + id;
    }

}
