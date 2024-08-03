package ru.konadren.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.konadren.springcourse.dao.BookDAO;
import ru.konadren.springcourse.models.Book;

@Controller
@RequestMapping("/librarian/books")
public class BookController {

    private final BookDAO dao;

    @Autowired
    public BookController(BookDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", dao.index());
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
        dao.save(book);
        return "redirect:/librarian/books";
    }


    // просто показываем человека и книги закрепленные за ним
    @GetMapping("/{id}")
    public String showCurrentBook(@PathVariable("id") Integer id, Model model){
        //todo: извлекаем по айди и добавляем атрибут в модель
        model.addAttribute("book", dao.show(id));
        return "books/currentBookPage";
    }


    // с currentPersonPage при клике на кнопку совершается переход на editPage
    @GetMapping("/{id}/edit")
    public String goToEditPersonPage(Model model, @PathVariable("id") int id){
        model.addAttribute("book", dao.show(id));
        return  "books/editPage";
    }

    // обновляем данные о человеке после перехода на editPage
    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult binding, @PathVariable("id") int id){
        //validator.validate(person, bindingResult);

        if (binding.hasErrors()) return "books/editPage";
        dao.update(id, book);
        return "redirect:/librarian/books";
    }

    // на currentPersonPage кнопка delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        dao.delete(id);
        return "redirect:/librarian/books";
    }
}
