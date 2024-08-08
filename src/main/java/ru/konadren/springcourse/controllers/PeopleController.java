package ru.konadren.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.konadren.springcourse.models.Person;
import ru.konadren.springcourse.services.PeopleService;

@Controller
@RequestMapping("/librarian/people")
public class PeopleController {

    private final PeopleService peopleService;


    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    // страница, отображающая всех людей
    // по клику на человека совершается переход на currentPersonPage
    @GetMapping()
    public String index(Model model){
        //todo: получение всех людей из DAO и отображение во вьюхе
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    // если на /people нажать на кнопку "добавить человека"
    // совершается переход на страницу addingPage
    @GetMapping("/newPerson")
    public String goToAddingPage(@ModelAttribute("person") Person person){
        return "people/addingPage";
    }

    // при клике на "сохранить человека" вызывается этот метод из addingPage
    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult binding){
        //todo: валидатор
        if (binding.hasErrors()) return "people/addingPage";

        peopleService.save(person);
        return "redirect:/librarian/people";
    }


    // просто показываем человека и книги закрепленные за ним
    @GetMapping("/{id}")
    public String showCurrentPerson(@PathVariable("id") Integer id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));
        return "people/currentPersonPage";
    }


    // с currentPersonPage при клике на кнопку совершается переход на editPage
    @GetMapping("/{id}/edit")
    public String goToEditPersonPage(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return  "people/editPage";
    }

    // обновляем данные о человеке после перехода на editPage
    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person,
                         BindingResult binding, @PathVariable("id") int id){
        //validator.validate(person, bindingResult);

        if (binding.hasErrors()) return "people/editPage";
        peopleService.update(id, person);
        return "redirect:/librarian/people";
    }

    // на currentPersonPage кнопка delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/librarian/people";
    }

}
