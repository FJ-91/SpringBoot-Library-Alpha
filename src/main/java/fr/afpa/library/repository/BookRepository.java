package fr.afpa.library.repository;

import fr.afpa.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Fahd Jaouad
 */

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    public Book findByIsbn(String isbn);

    public Book findByTitle(String title);

    public List<Book> findByAvailableIsTrue();
}
