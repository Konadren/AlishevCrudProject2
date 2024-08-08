package ru.konadren.springcourse.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.konadren.springcourse.models.Book;
import ru.konadren.springcourse.models.Person;
import ru.konadren.springcourse.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Book findOne(int id){
        Optional<Book> byId = booksRepository.findById(id);
        return byId.orElse(null);
    }

    @Transactional
    public void save(Book person){
        booksRepository.save(person);
    }

    @Transactional
    public void update(int id, Book updatedPerson) {
        updatedPerson.setId(id);
        booksRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(Integer id) {
        return null;
    }

    public void assign(int id, Person person) {
    }

    public void release(int id) {

    }
}
