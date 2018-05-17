package fr.afpa.library.repository;

import fr.afpa.library.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface AuthorRepository extends CrudRepository<Author, Long> {

    public Author findByFirstnameAndLastname(String firstname, String lastname);

}
