package fr.afpa.library.service;

import fr.afpa.library.model.Book;
import fr.afpa.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Book service class implementing the BookService interface
 *  @author Fahd Jaouad
 */

@Service
public class BookServiceImpl implements BookService {

    //Reference to the book repository (also known as the DAO) using Spring's dependency injection
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByAvailableIsTrue() {
        return bookRepository.findByAvailableIsTrue();
    }
}
