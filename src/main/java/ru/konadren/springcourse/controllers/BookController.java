package ru.konadren.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.konadren.springcourse.models.Book;

@Controller
@RequestMapping("/librarian/books")
public class BookController {
    @GetMapping()
    public String index(Model model){
        //todo: получение всех людей из DAO и отображение во вьюхе
        return "books/index";
    }

    // если на /people нажать на кнопку "добавить человека"
    // совершается переход на страницу addingPage
    @GetMapping("/newBook")
    public String goToAddingPage(Model model){
        return "books/addingPage";
    }

    // при клике на "сохранить человека" вызывается этот метод из addingPage
    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult binding){
        //todo: валидатор
        if (binding.hasErrors()) return "book/addingPage";
        //todo: сохранение человека (метод из ДАО)
        return "redirect:/librarian/books";
    }


    // просто показываем человека и книги закрепленные за ним
    @GetMapping("/{id}")
    public String showCurrentBook(@PathVariable("id") Integer id, Model model){
        //todo: извлекаем по айди и добавляем атрибут в модель
        return "people/currentPersonPage";
    }


    // с currentPersonPage при клике на кнопку совершается переход на editPage
    @GetMapping("/{id}/edit")
    public String goToEditPersonPage(@PathVariable("id") int id, Model model){
        //todo: добавление в модель
        return  "books/editPage";
    }

    // обновляем данные о человеке после перехода на editPage
    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("book") @Valid Book book,
                             BindingResult binding, @PathVariable("id") int id){
        //validator.validate(person, bindingResult);

        if (binding.hasErrors()) return "book/editPage";
        //personDAO.update(id, person);
        return "redirect:/librarian/books";
    }

    // на currentPersonPage кнопка delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        // personDAO.delete(id);
        return "redirect:/librarian/books";
    }
}
