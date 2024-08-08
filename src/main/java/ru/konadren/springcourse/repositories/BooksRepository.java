package ru.konadren.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.konadren.springcourse.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
