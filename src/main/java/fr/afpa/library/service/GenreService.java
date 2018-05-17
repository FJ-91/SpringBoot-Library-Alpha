package fr.afpa.library.service;

import fr.afpa.library.model.Genre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GenreService {

    public void save(Genre genre);

    public boolean exists(int id);

    public void delete(int id);

    public Genre findOne(int id);

    public List<Genre> findAll();

    public Genre findByName(String name);

}
