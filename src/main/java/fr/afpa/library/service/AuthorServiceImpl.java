package fr.afpa.library.service;

import fr.afpa.library.model.Author;
import fr.afpa.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.delete(id);
    }

    @Override
    public Author findOne(Long id) {
        return authorRepository.findOne(id);
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public Author findByFirstnameAndLastname(String firstname, String lastname) {
        return authorRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    @Override
    public boolean exists(Long id) {
        return authorRepository.exists(id);
    }
}
