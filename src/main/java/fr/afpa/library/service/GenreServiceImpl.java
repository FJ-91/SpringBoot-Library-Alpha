package fr.afpa.library.service;

import fr.afpa.library.model.Genre;
import fr.afpa.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public boolean exists(int id) {
        return genreRepository.exists(id);
    }

    @Override
    public void delete(int id) {
        genreRepository.delete(id);
    }

    @Override
    public Genre findOne(int id) {
        return genreRepository.findOne(id);
    }

    @Override
    public List<Genre> findAll() {
        return (List<Genre>) genreRepository.findAll();
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }
}
