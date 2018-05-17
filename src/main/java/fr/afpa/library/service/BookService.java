package fr.afpa.library.service;

import fr.afpa.library.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {

    public void save(Book book);

    public void delete(Long id);

    public Book findOne(Long id);

    public Book findByIsbn(String isbn);

    public List<Book> findAll();

    public Book findByTitle(String title);

    public List<Book> findByAvailableIsTrue();

}
