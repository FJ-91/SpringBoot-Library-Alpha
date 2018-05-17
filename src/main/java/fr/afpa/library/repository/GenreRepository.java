package fr.afpa.library.repository;

import fr.afpa.library.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
@RestResource(exported = false)
public interface GenreRepository extends CrudRepository<Genre, Integer> {

    public Genre findByName(String name);
}
