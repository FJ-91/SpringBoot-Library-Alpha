package fr.afpa.library.service;

import fr.afpa.library.model.Copy;
import fr.afpa.library.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Override
    public void save(Copy copy) {
        copyRepository.save(copy);
    }

    @Override
    public void delete(Long id) {
        copyRepository.delete(id);
    }

    @Override
    public Copy findOne(Long id) {
        return copyRepository.findOne(id);
    }

    @Override
    public List<Copy> findAll() {
        return (List<Copy>) copyRepository.findAll();
    }

    @Override
    public List<Copy> findByAvailableIsTrue() {
        return copyRepository.findByAvailableIsTrue();
    }
}
