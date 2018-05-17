package fr.afpa.library.service;

import fr.afpa.library.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorService {

    public void save(Author author);

    public void delete(Long id);

    public Author findOne(Long id);

    public List<Author> findAll();

    public Author findByFirstnameAndLastname(String firstname, String lastname);

    public boolean exists(Long id);
}
