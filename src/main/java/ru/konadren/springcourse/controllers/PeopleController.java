package ru.konadren.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.konadren.springcourse.dao.PersonDAO;
import ru.konadren.springcourse.models.Person;

@Controller
@RequestMapping("/librarian/people")
public class PeopleController {
    private final PersonDAO personDAO;


    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    // страница, отображающая всех людей
    // по клику на человека совершается переход на currentPersonPage
    @GetMapping()
    public String index(Model model){
        //todo: получение всех людей из DAO и отображение во вьюхе
        model.addAttribute("people", personDAO.index());
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

        personDAO.save(person);
        return "redirect:/librarian/people";
    }


    // просто показываем человека и книги закрепленные за ним
    @GetMapping("/{id}")
    public String showCurrentPerson(@PathVariable("id") Integer id, Model model){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/currentPersonPage";
    }


    // с currentPersonPage при клике на кнопку совершается переход на editPage
    @GetMapping("/{id}/edit")
    public String goToEditPersonPage(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return  "people/editPage";
    }

    // обновляем данные о человеке после перехода на editPage
    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person,
                         BindingResult binding, @PathVariable("id") int id){
        //validator.validate(person, bindingResult);

        if (binding.hasErrors()) return "people/editPage";
        personDAO.update(id, person);
        return "redirect:/librarian/people";
    }

    // на currentPersonPage кнопка delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/librarian/people";
    }

}
