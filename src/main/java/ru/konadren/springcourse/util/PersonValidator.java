package ru.konadren.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.konadren.springcourse.dao.PersonDAO;
import ru.konadren.springcourse.models.Person;

//@Component
//public class PersonValidator implements Validator {
//    private final PersonDAO dao;
//
//    @Autowired
//    public PersonValidator(PersonDAO dao) {
//        this.dao = dao;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Person.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Person person = (Person) target;
//
////        if (dao.show(person.getEmail()).isPresent()) {
////            errors.rejectValue("email", "", "This email is already taken");
////        }
//    }
//}