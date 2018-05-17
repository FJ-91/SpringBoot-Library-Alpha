package fr.afpa.library.service;

import fr.afpa.library.model.Copy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CopyService {

    public void save(Copy copy);

    public void delete(Long id);

    public Copy findOne(Long id);

    public List<Copy> findAll();

    public List<Copy> findByAvailableIsTrue();

}
