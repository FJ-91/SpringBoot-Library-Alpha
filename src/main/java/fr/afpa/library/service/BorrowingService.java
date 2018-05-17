package fr.afpa.library.service;

import fr.afpa.library.model.Borrowing;
import fr.afpa.library.exception.BorrowingNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BorrowingService {

    public void save(Borrowing borrowing);

    public void delete(Long id) throws BorrowingNotFoundException;

    public Borrowing findOne(Long id) throws BorrowingNotFoundException;

    public List<Borrowing> findAll();

    public boolean exists(Long id);

}
